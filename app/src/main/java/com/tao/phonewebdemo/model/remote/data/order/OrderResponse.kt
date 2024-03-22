package com.tao.phonewebdemo.model.remote.data.order

data class OrderResponse(
    val status: Int,
    val message: String,
    val order_id: Int
)