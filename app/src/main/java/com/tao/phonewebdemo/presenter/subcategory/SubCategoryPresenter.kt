package com.tao.phonewebdemo.presenter.subcategory

import com.tao.phonewebdemo.model.remote.OperationalCallback
import com.tao.phonewebdemo.model.remote.data.subcategory.SubCategoryProductResponse
import com.tao.phonewebdemo.model.remote.data.subcategory.SubCategoryResponse
import com.tao.phonewebdemo.model.remote.volleyhandler.SubCategoryVolleyHandler

class SubCategoryPresenter(private val volleyHandler: SubCategoryVolleyHandler, private val subCategoryView: SubCategoryMVP.SubCategoryView): SubCategoryMVP.SubCategoryPresenter {
    override fun loadSubCategoryList(categoryId: String): String {
        subCategoryView.onLoad(true)
        val message = volleyHandler.callSubCategoryListApi(categoryId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    subCategoryView.setResult(data as SubCategoryResponse)
                    subCategoryView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    subCategoryView.onLoad(false)
                }
            })
        return message
    }

    override fun loadSubCategoryProducts(subCategoryId: String): String {
        subCategoryView.onLoad(true)
        val message = volleyHandler.callSubCategoryProductListApi(subCategoryId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    subCategoryView.setResult(data as SubCategoryProductResponse)
                    subCategoryView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    subCategoryView.onLoad(false)
                }
            })
        return message
    }


}