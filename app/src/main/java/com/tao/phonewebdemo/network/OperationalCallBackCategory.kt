package com.tao.phonewebdemo.network

import com.tao.phonewebdemo.model.CategoryResponse

interface OperationalCallBackCategory {

    fun onSuccess(categoryResponse: CategoryResponse)
    fun onFailure(message: String)

}