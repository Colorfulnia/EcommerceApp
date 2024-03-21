package com.tao.phonewebdemo.model.remote.volleyhandler

import android.content.Context
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.BASE_URL_LOGIN
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.BASE_URL_REGISTRATION
import com.tao.phonewebdemo.model.remote.data.login.LoginOperationalCallback
import com.tao.phonewebdemo.model.remote.data.register.RegisterOperationalCallBack

class UserVolleyHandler(private val context: Context) {

    fun postRegistration(email_id: String,full_name: String,mobile_no: String,password: String,callBack: RegisterOperationalCallBack) {
        val requestQueue = Volley.newRequestQueue(context)
        val params = HashMap<String, String>()
        params["email_id"] = email_id
        params["full_name"] = full_name
        params["mobile_no"] = mobile_no
        params["password"] = password
        val stringRequest = object : StringRequest(
            Method.POST, BASE_URL_REGISTRATION, { response ->
                callBack.onSuccessRegistration(0,response)

            }, {
                    error->
                callBack.onFailureRegistration(1,error.message?:"unknown error")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
    fun postLogin(email_id: String,password: String,callBack: LoginOperationalCallback) {
        val requestQueue = Volley.newRequestQueue(context)
        val params = HashMap<String, String>()
        params["email_id"] = email_id
        params["password"] = password
        val stringRequest = object : StringRequest(
            Method.POST, BASE_URL_LOGIN, { response ->
                callBack.onSuccessLogin(0,response)

            }, {
                    error->
                callBack.onFailureLogin(1,error.message?:"Failed to login. Please check your email id or password")
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        requestQueue.add(stringRequest)
    }


}