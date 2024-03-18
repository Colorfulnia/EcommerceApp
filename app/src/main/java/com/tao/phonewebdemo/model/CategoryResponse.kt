package com.tao.phonewebdemo.model

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)
