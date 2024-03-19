package com.tao.phonewebdemo.presenter.category

import com.tao.phonewebdemo.model.remote.data.category.CategoryResponse

interface MVPCategory {
    interface CategoryPresenter{
        fun fetchCategoryData()
    }
    interface CategoryView{
        fun setResultCategory(categoryResponse: CategoryResponse)
        fun showErrorCategory(message: String)
    }
}