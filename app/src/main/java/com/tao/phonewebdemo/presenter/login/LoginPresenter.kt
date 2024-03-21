package com.tao.phonewebdemo.presenter.login

import com.tao.phonewebdemo.model.remote.data.login.LoginData
import com.tao.phonewebdemo.model.remote.data.login.LoginOperationalCallback
import com.tao.phonewebdemo.model.remote.volleyhandler.UserVolleyHandler

class LoginPresenter(private val volleyHandler: UserVolleyHandler, private val loginView: LoginMVP.LoginView):LoginMVP.LoginPresenter {
    override fun sendLoginData(data: LoginData) {
        volleyHandler.postLogin(data.email_id,data.password,object: LoginOperationalCallback {
            override fun onSuccessLogin(status: Int, message: String) {
                loginView.setResultLogin(0,message)
            }

            override fun onFailureLogin(status: Int, error: String) {
                loginView.setResultLogin(1,error)
            }
        })
    }
}