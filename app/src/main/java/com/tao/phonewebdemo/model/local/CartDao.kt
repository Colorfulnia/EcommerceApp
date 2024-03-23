package com.tao.phonewebdemo.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Insert
    fun insertCart(cart: Cart)

    @Delete
    fun deleteCart(cart : Cart)

    @Update
    fun updateCart(cart: Cart)

    @Query("SELECT * FROM Cart")
    fun getAllCart(): List<Cart>

    @Query("UPDATE Cart SET quantity = :newQuantity WHERE id = :pos")
    fun updateQuantity(pos: Long, newQuantity: String)

    @Query("DELETE FROM Cart WHERE id = :cartId")
    fun deleteCartById(cartId: Long):Unit

}