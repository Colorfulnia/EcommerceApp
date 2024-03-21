package com.tao.phonewebdemo.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tao.phonewebdemo.R

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.tao.phonewebdemo.databinding.ActivityCategoryBinding
import com.tao.phonewebdemo.model.remote.data.category.CategoryData
import com.tao.phonewebdemo.model.remote.data.category.CategoryResponse
import com.tao.phonewebdemo.model.remote.volleyhandler.VolleyCategoryHandler
import com.tao.phonewebdemo.presenter.category.CategoryPresenter
import com.tao.phonewebdemo.presenter.category.MVPCategory
import com.tao.phonewebdemo.view.adapter.CategoryAdapter

class CategoryActivity : AppCompatActivity(), MVPCategory.CategoryView {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var presenter: CategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CategoryPresenter(VolleyCategoryHandler(this), this)
        presenter.fetchCategoryData()

        binding.recyclerViewCategory.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }

    override fun sendSubCategory(url: String) {
        val intent = Intent(this, SubCategoryActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    override fun setResultCategory(categoryResponse: CategoryResponse) {
        Log.d("success", categoryResponse.toString())
        val categories = ArrayList<CategoryData>()
        categoryResponse.categories.forEach { category ->
            categories.add(CategoryData(category.category_name, category.category_image_url))
        }
        val categoryAdapter = CategoryAdapter(this, categories)
        binding.recyclerViewCategory.adapter = categoryAdapter
    }

    override fun showErrorCategory(message: String) {
        Log.d("Failure", message)
    }
}
