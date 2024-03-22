package com.tao.phonewebdemo.presenter.product

import com.tao.phonewebdemo.model.remote.OperationalCallback
import com.tao.phonewebdemo.model.remote.data.product.ProductDetailResponse
import com.tao.phonewebdemo.model.remote.volleyhandler.ProductVolleyHandler

class ProductPresenter(private val volleyHandler: ProductVolleyHandler, private val productView: ProductMVP.ProductView): ProductMVP.ProductPresenter {

    override fun getProduct(productId: String): String {
        productView.onLoad(true)
        val message = volleyHandler.callProductDetailApiCall(productId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    productView.setResult(data as ProductDetailResponse, true)
                    productView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    productView.setResult(message, false)
                    productView.onLoad(false)
                }

            })
        return message
    }


}