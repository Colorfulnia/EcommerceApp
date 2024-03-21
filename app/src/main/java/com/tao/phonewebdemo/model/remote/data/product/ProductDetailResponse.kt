package com.tao.phonewebdemo.model.remote.data.product

data class ProductDetailResponse(
    val message: String,
    val product: Product,
    val status: Int
)