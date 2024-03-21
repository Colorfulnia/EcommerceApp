package com.tao.phonewebdemo.model.remote.data.user

interface OperationalCallBackUser {
    fun onSuccess(status: Int, message: String)
    fun onFailure(message: String)
}