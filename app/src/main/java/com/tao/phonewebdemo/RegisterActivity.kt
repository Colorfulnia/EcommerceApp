package com.tao.phonewebdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.tao.phonewebdemo.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val userName = binding.editTextUserName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString()
        val reEnterPassword = binding.editTextReEnterPassword.text.toString()
        val agreeTerms = binding.checkBoxAgree.isChecked

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || reEnterPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != reEnterPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (!agreeTerms) {
            Toast.makeText(this, "You must agree to the terms", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

    }
}