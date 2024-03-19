package com.tao.phonewebdemo.view.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tao.phonewebdemo.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPref()

        binding.register.setOnClickListener {
            getDetails()
        }

        binding.login1.setOnClickListener {
            finish()
        }
    }

    private fun getDetails() {
        val email = binding.email.text.toString()
        val name = binding.name.text.toString()
        val pass = binding.password.text.toString()
        val repass = binding.repassword.text.toString()
        if (pass == repass) {
            with(sharedPreferences.edit()) {
                clear()
                putString("email", email)
                putString("name", name)
                putString("pass", pass)
                putString("repass", repass)
                apply()
            }
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Enter Matching passwords", Toast.LENGTH_SHORT).show()
            binding.name.text?.clear()
            binding.email.text?.clear()
            binding.password.text?.clear()
            binding.repassword.text?.clear()
        }
    }

    private fun initPref() {
        sharedPreferences = getSharedPreferences("Login Details", Context.MODE_PRIVATE)
    }
}
