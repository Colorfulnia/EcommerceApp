package com.tao.phonewebdemo.model.remote.data.register

interface RegisterOperationalCallBack {
    fun onSuccessRegistration(status: Int, response: String)
    fun onFailureRegistration(status: Int, error: String)
}