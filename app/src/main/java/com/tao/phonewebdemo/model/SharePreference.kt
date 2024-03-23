package com.tao.phonewebdemo.model

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//fun addCartItemLocally(newItem: CartItem): Boolean {
//    val cartJson = encryptedSharedPrefs.getString(PREF_CART, "")
//    val gson = Gson()
//    val token: TypeToken<MutableList<CartItem>> = object : TypeToken<MutableList<CartItem>>() {}
//    var newList: MutableList<CartItem>? = gson.fromJson(cartJson, token.type)
//    var added = false
//    if (newList == null) {
//        newList = mutableListOf()
//        newList.add(newItem)
//    } else {
//        for (i in newList.indices) {
//            if (newList[i].productId == newItem.productId) {
//                val copy = CartItem(
//                    newItem.productId,
//                    newItem.productName,
//                    newItem.productDescription,
//                    newItem.productPrice,
//                    newItem.productImage,
//                    newItem.amount + newList[i].amount
//                )
//                newList[i] = copy
//                added = true
//            }
//        }
//        if (!added) newList.add(newItem)
//    }
//
//    val editor = encryptedSharedPrefs.edit()
//    editor.putString(PREF_CART, gson.toJson(newList, token.type))
//    return editor.commit()
//}