package com.tao.phonewebdemo.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tao.phonewebdemo.model.Category
import com.tao.phonewebdemo.model.CategoryResponse
import com.tao.phonewebdemo.model.Constant.CATEGORY_URL
import org.json.JSONObject

class ApiServiceImpl(private val context: Context) {
    private val requestQueue by lazy { Volley.newRequestQueue(context)}
    fun makeApiCategoryCall(callback: OperationalCallBackCategory){
        val categoryRequest= StringRequest(
            Request.Method.GET,
            CATEGORY_URL,{
                val typeToken=object : TypeToken<CategoryResponse>(){}
                val response= Gson().fromJson(it,typeToken)
                Log.d("tag volley",response.toString())
                callback.onSuccess(response)
            },{
                Log.d("tag volley",it.toString())
                callback.onFailure(it.toString())
            }

        )
        requestQueue.add(categoryRequest)

    }
}
