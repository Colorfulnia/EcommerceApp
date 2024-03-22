package com.tao.phonewebdemo.presenter.product

interface ProductMVP {
    interface ProductPresenter {
        fun getProduct(productId: String): String
    }

    interface ProductView {
        fun setResult(data: Any, successed: Boolean)
        fun onLoad(isLoading: Boolean)
    }
}