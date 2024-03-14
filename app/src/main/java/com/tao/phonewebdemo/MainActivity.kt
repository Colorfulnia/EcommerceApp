package com.tao.phonewebdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tao.phonewebdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Super Cart"

        val menuButton = ImageButton(this).apply {
            setImageResource(R.drawable.ic_menu)
            setBackgroundResource(R.attr.selectableItemBackgroundBorderless)
            setOnClickListener {
                showBottomSheetMenu()
            }
        }

        binding.toolbar.addView(menuButton)
    }

    private fun showBottomSheetMenu() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_menu, null)
        bottomSheetDialog.setContentView(sheetView)

        sheetView.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            bottomSheetDialog.dismiss()
        }

        sheetView.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    val loginButton: Button = findViewById(R.id.loginButton)
    loginButton.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    val registerButton: Button = findViewById(R.id.registerButton)
    registerButton.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
