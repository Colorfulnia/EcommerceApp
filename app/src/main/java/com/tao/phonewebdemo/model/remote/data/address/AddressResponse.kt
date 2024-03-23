package com.tao.phonewebdemo.model.remote.data.address

data class AddressResponse(
    val addresses: List<Addresse>,
    val message: String,
    val status: Int
)