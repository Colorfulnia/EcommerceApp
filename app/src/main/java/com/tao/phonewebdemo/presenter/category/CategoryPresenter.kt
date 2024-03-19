package com.tao.phonewebdemo.presenter.category

import com.tao.phonewebdemo.model.remote.OperationalCallBackCategory
import com.tao.phonewebdemo.model.remote.data.category.CategoryResponse
import com.tao.phonewebdemo.model.remote.volleyhandler.VolleyCategoryHandler

class CategoryPresenter(
    private val volleyCategoryHandler: VolleyCategoryHandler,
    private val categoryView: MVPCategory.CategoryView
): MVPCategory.CategoryPresenter {
    override fun fetchCategoryData(){
        volleyCategoryHandler.makeApiCategoryCall(object : OperationalCallBackCategory {
            override fun onSuccess(categoryResponse: CategoryResponse) {
                categoryView.setResultCategory(categoryResponse)
            }

            override fun onFailure(message: String) {
                categoryView.showErrorCategory(message)
            }
        })
    }
}