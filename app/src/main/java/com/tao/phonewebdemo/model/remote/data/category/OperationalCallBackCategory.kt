package com.tao.phonewebdemo.model.remote.data.category

import com.tao.phonewebdemo.model.remote.data.category.CategoryResponse

interface OperationalCallBackCategory {

    fun onSuccess(categoryResponse: CategoryResponse)
    fun onFailure(message: String)

}