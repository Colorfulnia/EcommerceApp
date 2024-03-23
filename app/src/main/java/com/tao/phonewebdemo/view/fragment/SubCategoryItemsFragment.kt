package com.tao.phonewebdemo.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tao.phonewebdemo.databinding.FragmentSubcategoryItemBinding
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.SUB_CATEGORY_ID
import com.tao.phonewebdemo.model.remote.data.product.CommunicatorProduct
import com.tao.phonewebdemo.model.remote.data.subcategory.Product
import com.tao.phonewebdemo.model.remote.data.subcategory.SubCategoryProductResponse
import com.tao.phonewebdemo.model.remote.volleyhandler.SubCategoryVolleyHandler
import com.tao.phonewebdemo.presenter.subcategory.SubCategoryMVP
import com.tao.phonewebdemo.presenter.subcategory.SubCategoryPresenter
import com.tao.phonewebdemo.view.activity.ProductActivity
import com.tao.phonewebdemo.view.adapter.SubCategoryAdapter




class SubcategoryItemsFragment : Fragment(), SubCategoryMVP.SubCategoryView, CommunicatorProduct {
    private lateinit var binding: FragmentSubcategoryItemBinding
    private lateinit var subCategoryId: String
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var productList: List<Product>
    private lateinit var adapter: SubCategoryAdapter<Any?>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubcategoryItemBinding.inflate(layoutInflater, container, false)
        subCategoryId = arguments?.getString(SUB_CATEGORY_ID).toString()
        Log.d("hello",subCategoryId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        presenter = SubCategoryPresenter(SubCategoryVolleyHandler(view.context), this)
        presenter.loadSubCategoryProducts(subCategoryId)
    }

    override fun setResult(data: Any) {
        productList = (data as SubCategoryProductResponse).products
        adapter = SubCategoryAdapter(productList,this)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProduct.adapter = adapter
        if (productList.isEmpty()) {
            binding.noItem.visibility = View.VISIBLE
        } else {
            binding.noItem.visibility = View.GONE
        }
    }

    override fun sendCode(id: String) {
        val intent = Intent(requireContext(), ProductActivity::class.java).apply {
            putExtra("ProductId", id)
        }
        startActivity(intent)
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}