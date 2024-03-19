package com.tao.phonewebdemo.model.remote

interface OperationalCallback {
    fun onSuccess(data: Any)
    fun onFailure(message: String)
}