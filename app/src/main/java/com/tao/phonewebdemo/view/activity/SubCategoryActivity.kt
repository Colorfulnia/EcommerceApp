package com.tao.phonewebdemo.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tao.phonewebdemo.R
import com.tao.phonewebdemo.databinding.ActivitySubCategoryBinding
import com.tao.phonewebdemo.model.remote.data.subcategory.Subcategory
import com.tao.phonewebdemo.model.remote.volleyhandler.SubCategoryVolleyHandler
import com.tao.phonewebdemo.presenter.subcategory.SubCategoryMVP
import com.tao.phonewebdemo.presenter.subcategory.SubCategoryPresenter

class SubCategoryActivity : AppCompatActivity(), SubCategoryMVP.SubCategoryView {
    private lateinit var binding: ActivitySubCategoryBinding
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var subCategoryList: ArrayList<Subcategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryId = intent.getStringExtra("URL").toString()
        getSubCategoryList(categoryId)
    }

    private fun getSubCategoryList(categoryId: String) {
        presenter = SubCategoryPresenter(SubCategoryVolleyHandler(this), this)
        presenter.loadSubCategoryList(categoryId)
    }

    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(this, subCategoryList)
        binding.viewpager.adapter = adapter
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = subCategoryList[position].subcategory_name
        }.attach()
    }

    override fun setResult(data: Any) {
        if (data is SubCategoryResponse) {
            subCategoryList = data.subcategories
            Log.e("SubCategoryActivity", data.toString())

            setUpViewPager()
            setUpTabLayout()
        } else {
            Log.e("SubCategoryActivity", "Unexpected data type: ${data.javaClass.simpleName}")
        }
    }

    override fun onLoad(isLoading: Boolean) {
        Log.i("SubCategoryActivity", isLoading.toString())
    }
}