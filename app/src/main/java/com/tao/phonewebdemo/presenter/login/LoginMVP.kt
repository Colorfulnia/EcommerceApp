package com.tao.phonewebdemo.presenter.login

import com.tao.phonewebdemo.model.remote.data.login.LoginData

interface LoginMVP {
    interface LoginPresenter{
        fun sendLoginData(data: LoginData)
    }
    interface LoginView{
        fun setResultLogin(status:Int, message:String)
    }
}