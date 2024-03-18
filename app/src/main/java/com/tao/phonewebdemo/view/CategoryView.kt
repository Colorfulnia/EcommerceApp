package com.tao.phonewebdemo.view

import com.tao.phonewebdemo.model.Category

interface CategoryView {
    fun showCategories(categories: List<Category>)
    fun showError(message: String)
}
