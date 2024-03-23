package com.tao.phonewebdemo.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val orderid: Long,
    val orderDetails: String? ="",
    val price: String?="",
    val personName : String?="",
    val address : String?="",
    val personNumber : String?="",
    val cardnumber: String?="",
    val cardName: String?="",
    val cvv: String?="",
    val expdate: String?=""
)