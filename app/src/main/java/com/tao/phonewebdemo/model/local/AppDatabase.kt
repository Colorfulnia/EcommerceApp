package com.tao.phonewebdemo.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cart::class,Order::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao
    abstract fun getOrderDao(): OrderDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "ShopCart"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}