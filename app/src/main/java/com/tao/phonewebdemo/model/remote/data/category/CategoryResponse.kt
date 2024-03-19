package com.tao.phonewebdemo.model.remote.data.category

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)