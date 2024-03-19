package com.tao.phonewebdemo.model.remote.data.subcategory

data class SubCategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: ArrayList<Subcategory>
)