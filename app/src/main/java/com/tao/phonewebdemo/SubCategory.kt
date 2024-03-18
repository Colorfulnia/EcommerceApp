package com.tao.phonewebdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tao.phonewebdemo.databinding.ActivitySubCategoryBinding
import com.tao.phonewebdemo.model.SubCategoryItem

class SubCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubCategoryBinding
    private lateinit var adapter: SubCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarSubCategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbarSubCategory.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        val items = fetchDataForSubCategory()

        adapter = SubCategoryAdapter(items) { subCategoryItem ->
            // Handle 'Add to Cart' clicked, for example:
            // Add subCategoryItem to the cart, show a toast, etc.
        }

        binding.recyclerViewItems.adapter = adapter
    }

    private fun fetchDataForSubCategory(): List<SubCategoryItem> {

        return emptyList()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}