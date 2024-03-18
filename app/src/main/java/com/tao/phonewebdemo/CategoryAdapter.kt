package com.tao.phonewebdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tao.phonewebdemo.model.Category

class CategoryAdapter(
    private var categories: MutableList<Category>,
    private val onCategoryClicked: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view, onCategoryClicked)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    fun updateCategories(newCategories: List<Category>) {
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(
        itemView: View,
        private val onCategoryClicked: (Category) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        private val imageViewCategory: ImageView = itemView.findViewById(R.id.imageViewCategory)

        fun bind(category: Category) {
            textViewCategory.text = category.category_name
            if (category.category_image_url.isNotEmpty()) {
                Picasso.get().load(category.category_image_url).into(imageViewCategory)
            }

            itemView.setOnClickListener { onCategoryClicked(category) }
        }
    }
}
