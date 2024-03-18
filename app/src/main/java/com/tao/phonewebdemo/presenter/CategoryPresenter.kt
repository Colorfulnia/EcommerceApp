package com.tao.phonewebdemo.presenter

import com.tao.phonewebdemo.model.CategoryResponse
import com.tao.phonewebdemo.view.CategoryView
import com.tao.phonewebdemo.network.ApiServiceImpl
import com.tao.phonewebdemo.network.OperationalCallBackCategory

class CategoryPresenter(private val view: CategoryView, private val apiService: ApiServiceImpl) {

    fun loadCategories() {
        apiService.makeApiCategoryCall(object : OperationalCallBackCategory {
            override fun onSuccess(categoryResponse: CategoryResponse) {
                if (categoryResponse.status == 0) {
                    val activeCategories = categoryResponse.categories.filter { it.is_active == "1" }
                    view.showCategories(activeCategories)
                } else {
                    view.showError(categoryResponse.message)
                }
            }

            override fun onFailure(message: String) {
                view.showError(message)
            }
        })
    }
}


