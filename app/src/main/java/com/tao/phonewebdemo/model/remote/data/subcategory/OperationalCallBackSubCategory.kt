package com.tao.phonewebdemo.model.remote.data.subcategory

import com.tao.phonewebdemo.model.remote.data.subcategory.SubCategoryResponse
interface OperationalCallBackSubCategory {
    fun onSuccessSub(subCategoryResponse: SubCategoryResponse)
    fun onFailureSub(message: String)
}