package com.tao.phonewebdemo.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.tao.phonewebdemo.R
import com.tao.phonewebdemo.databinding.ActivityProductAcitivtyBinding
import com.tao.phonewebdemo.model.addCartItemLocally
import com.tao.phonewebdemo.model.remote.data.CartItem

import com.tao.phonewebdemo.model.remote.data.Constant.Constants.PRODUCT_ID
import com.tao.phonewebdemo.model.remote.data.product.Image
import com.tao.phonewebdemo.model.remote.data.product.ProductDetail
import com.tao.phonewebdemo.model.remote.data.product.ProductDetailResponse
import com.tao.phonewebdemo.model.remote.data.product.Review
import com.tao.phonewebdemo.model.remote.data.product.Specification
import com.tao.phonewebdemo.model.remote.volleyhandler.ProductVolleyHandler
import com.tao.phonewebdemo.presenter.product.ProductMVP
import com.tao.phonewebdemo.presenter.product.ProductPresenter
import com.tao.phonewebdemo.view.adapter.ProductViewPagerAdapter
import com.tao.phonewebdemo.view.adapter.ReviewAdapter

class ProductActivity : AppCompatActivity(), ProductMVP.ProductView {
    private lateinit var binding:ActivityProductAcitivtyBinding
    private lateinit var presenter: ProductPresenter
//    private lateinit var encryptedSharedPreferences: SharedPreferences
    private lateinit var productDetail: ProductDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductAcitivtyBinding.inflate(layoutInflater).apply {
            setContentView(root)

            intent.extras?.getString(PRODUCT_ID)?.let { productId ->
                presenter = ProductPresenter(ProductVolleyHandler(this@ProductActivity), this@ProductActivity)
                getProduct(productId)
            }

//            encryptedSharedPreferences = getEncryptedPrefs(this@ProductActivity)
            setUpEvents()
        }
    }

    private fun setUpEvents() {
        binding.btnAddToCart.setOnClickListener {
            addCartItemLocally(
                CartItem(
                    productDetail.product_id,
                    productDetail.product_name,
                    productDetail.description,
                    productDetail.price,
                    productDetail.product_image_url,
                    binding.numberPicker.value
                )
            )
            openCartDialog()
        }
    }

    private fun openCartDialog() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Thank you!")
            .setMessage("Successfully added to cart")
            .setIcon(R.drawable.baseline_shopping_cart_checkout_24)
            .setNeutralButton("Keep Shopping", null)
            .setPositiveButton("Go to Cart") { _, _ -> moveToCart() }
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun moveToCart() {
        val intent = Intent(this@ProductActivity, MainActivity::class.java)
        intent.putExtra("cart", true)
        startActivity(intent)
        finish()
    }

    private fun getProduct(productId: String) {
        presenter.getProduct(productId)
    }

    override fun setResult(data: Any, successed: Boolean) {
        if (successed) {
            productDetail = (data as ProductDetailResponse).product
            setUpViews(productDetail)
        }
    }

    private fun setUpViews(productDetail: ProductDetail) {
        binding.apply {
            textProductName.text = productDetail.product_name
            val price = "$ " + productDetail.price
            textProductPrice.text = price
            textProductDescription.text = productDetail.description
            ratingBarProduct.rating = productDetail.average_rating.toFloat()

            setUpImageViewPager(productDetail.images)
            setUpReviewRecyclerView(productDetail.reviews)
            setUpSpecificationTable(productDetail.specifications)
        }
    }

    private fun setUpSpecificationTable(specifications: ArrayList<Specification>) {
        if (specifications.isEmpty()) {
            binding.noSpecification.visibility = View.VISIBLE
        } else {
            binding.noSpecification.visibility = View.GONE
        }
        for (specification in specifications) {
            val row = TableRow(applicationContext)
            val title = TextView(applicationContext)
            val detail = TextView(applicationContext)

            val layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            layoutParams.setMargins(0,0,0,15)

            title.layoutParams = layoutParams
            detail.layoutParams = layoutParams

            title.text = specification.title
            title.textSize = 15F
            detail.text = specification.specification
            detail.textSize = 15F
            detail.setTextColor(getResources().getColor(R.color.myPurple))

            row.addView(title)
            row.addView(detail)
            binding.tableLayoutSpecification.addView(row)
        }

    }

    private fun setUpReviewRecyclerView(reviews: ArrayList<Review>) {
        val adapter = ReviewAdapter(reviews)
        binding.recyclerViewReview.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewReview.adapter = adapter
        if (reviews.isEmpty()) {
            binding.noReviews.visibility = View.VISIBLE
        }
    }

    private fun setUpImageViewPager(images: ArrayList<Image>) {
        val adapter = ProductViewPagerAdapter(images)
        binding.viewPagerImageProduct.adapter = adapter

        TabLayoutMediator(binding.tabDots, binding.viewPagerImageProduct) { _, _ ->
        }.attach()
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}