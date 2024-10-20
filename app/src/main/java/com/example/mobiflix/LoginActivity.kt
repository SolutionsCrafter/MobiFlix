package com.example.mobiflix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initUI(){
        val etUserName:EditText = findViewById(R.id.userNameET)
        val etPassword:EditText = findViewById(R.id.passwordET)
        val btnLogIn:Button = findViewById(R.id.loginBTN)

        var userName = etUserName
        var password = etPassword

        btnLogIn.setOnClickListener {
            when {
                etUserName.text.toString().isEmpty() || etPassword.text.toString().isEmpty() -> {
                    if (etUserName.text.toString().isEmpty()) {
                        Toast.makeText(this@LoginActivity, "Please fill Username", Toast.LENGTH_SHORT).show()
                    }
                    if (etPassword.text.toString().isEmpty()) {
                        Toast.makeText(this@LoginActivity, "Please fill Password", Toast.LENGTH_SHORT).show()
                    }
                }
                etUserName.text.toString().trim() != "tttt" || etPassword.text.toString() != "tttt" -> {
                    if (etUserName.text.toString().trim() != "tttt") {
                        Toast.makeText(this@LoginActivity, "Please enter correct username", Toast.LENGTH_SHORT).show()
                    }
                    if (etPassword.text.toString() != "tttt") {
                        Toast.makeText(this@LoginActivity, "Please enter correct password", Toast.LENGTH_SHORT).show()
                    }
                }
                etUserName.text.toString().trim() == "tttt" && etPassword.text.toString() == "tttt" -> {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
            }
        }
    }
}