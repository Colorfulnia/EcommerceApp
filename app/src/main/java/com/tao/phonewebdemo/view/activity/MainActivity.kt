package com.tao.phonewebdemo.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.tao.phonewebdemo.R
import com.tao.phonewebdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPref()
        initHomeView()
    }

    private fun initPref() {
        sharedPreferences = getSharedPreferences("Login Details", Context.MODE_PRIVATE)
    }

    private fun initHomeView() {
        // home implementns in activity
        binding.homeMenu.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.categoryMenu -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                // Handle other menu items...
            }
            false
        }
    }
}
