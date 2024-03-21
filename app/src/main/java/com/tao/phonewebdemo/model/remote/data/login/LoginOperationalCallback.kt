package com.tao.phonewebdemo.model.remote.data.login

interface LoginOperationalCallback {

    fun onSuccessLogin(status: Int,message:String)
    fun onFailureLogin(status: Int, error: String)
}