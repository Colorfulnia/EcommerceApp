package com.tao.phonewebdemo.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Cart")
data class Cart(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val imageURL : String?="",
    val name : String?="",
    val price: String?="",
    val quantity: Long?=1

){
    constructor(imageURL: String?,name: String?,price:String?): this(0,imageURL,name,price)
}