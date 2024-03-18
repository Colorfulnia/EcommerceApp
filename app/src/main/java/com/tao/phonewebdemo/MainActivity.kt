package com.tao.phonewebdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tao.phonewebdemo.databinding.ActivityMainBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tao.phonewebdemo.model.Category
import com.tao.phonewebdemo.network.ApiServiceImpl
import com.tao.phonewebdemo.presenter.CategoryPresenter
import com.tao.phonewebdemo.view.CategoryView

class MainActivity : AppCompatActivity(), CategoryView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var presenter: CategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Super Cart"

        categoryAdapter = CategoryAdapter(mutableListOf()) { category ->
            val intent = Intent(this, SubCategoryActivity::class.java)
            intent.putExtra("category_id", category.category_id)
            startActivity(intent)
        }

        binding.recyclerViewCategories.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewCategories.adapter = categoryAdapter

        presenter = CategoryPresenter(this, ApiServiceImpl(this))
        presenter.loadCategories()
    }

    override fun showCategories(categories: List<Category>) {
        categoryAdapter.updateCategories(categories)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

