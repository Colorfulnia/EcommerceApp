package com.tao.phonewebdemo.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tao.phonewebdemo.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.tao.phonewebdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPref()

        binding.login.setOnClickListener {
            // fetchDetails()
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.signUp1.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun fetchDetails() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        val emailVerf = sharedPreferences.getString("email", "")
        val passVerf = sharedPreferences.getString("pass", "")

        if (email == emailVerf && password == passVerf) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            binding.email.text?.clear()
            binding.password.text?.clear()
            Toast.makeText(this, "Incorrect Details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initPref() {
        sharedPreferences = getSharedPreferences("Login Details", Context.MODE_PRIVATE)
    }
}
