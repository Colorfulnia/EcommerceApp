package com.tao.phonewebdemo.model.remote.volleyhandler

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tao.phonewebdemo.model.remote.OperationalCallBackCategory
import com.tao.phonewebdemo.model.remote.data.Constant.Constants.CATEGORY_URL
import com.tao.phonewebdemo.model.remote.data.category.CategoryResponse

class VolleyCategoryHandler(private val context: Context?){
    private val requestQueue by lazy { Volley.newRequestQueue(context)}

    fun makeApiCategoryCall(callback: OperationalCallBackCategory){
        val categoryRequest= StringRequest(
            Request.Method.GET,
            CATEGORY_URL,{
                val typeToken=object : TypeToken<CategoryResponse>(){}
                val response= Gson().fromJson(it,typeToken)
                Log.d("tag",response.toString())
                callback.onSuccess(response)
            },{
                Log.d("tag",it.toString())
                callback.onFailure(it.toString())
            }

        )
        requestQueue.add(categoryRequest)

    }
}