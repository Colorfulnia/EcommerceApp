package com.tao.phonewebdemo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tao.phonewebdemo.databinding.CategoryItemBinding
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.BASE_URL
import com.tao.phonewebdemo.model.remote.data.category.CategoryData
import com.tao.phonewebdemo.model.remote.data.subcategory.CommunicatorSubcategory

class CategoryAdapter(val categoryList: ArrayList<CategoryData>, private val c: CommunicatorSubcategory):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(private val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(categoryList: CategoryData){
            binding.categoryText.text=categoryList.categoryName
            var url=BASE_URL+categoryList.categoryImage
            Picasso.get().load(url.toString()).into(binding.categoryImage)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding= CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }



    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categoryList[position]
        holder.bind(currentCategory)

        holder.itemView.setOnClickListener(){
            var num=position+1
            var url= num.toString()
            c.sendSubCategory(url)
        }

    }
}