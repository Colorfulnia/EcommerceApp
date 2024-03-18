package com.tao.phonewebdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tao.phonewebdemo.databinding.ActivityMainBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Super Cart"

        setupCategoryRecyclerView()
    }

    private fun setupCategoryRecyclerView() {
        binding.recyclerViewCategories.layoutManager = GridLayoutManager(this, 2) // Sets up a grid with 2 columns
        categoryAdapter = CategoryAdapter(fetchCategories()) { category ->
            // TODO: Replace with actual category click handling
            val intent = Intent(this, SubCategoryActivity::class.java)
            startActivity(intent)
        }
        binding.recyclerViewCategories.adapter = categoryAdapter
    }

    private fun fetchCategories(): List<Category> {
        // Dummy data
        return listOf(
            Category("1", "Smart Phones"),
            Category("2", "Laptops"),
            // Add categories
        )
    }
}

data class Category(
    val id: String,
    val name: String
)

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClicked: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, onCategoryClicked)
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, onCategoryClicked: (Category) -> Unit) {
            itemView.findViewById<TextView>(R.id.textViewCategory).text = category.name
            itemView.setOnClickListener { onCategoryClicked(category) }
        }
    }
}