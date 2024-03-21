package com.tao.phonewebdemo.model.remote.data.subcategory

data class SubCategoryProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)