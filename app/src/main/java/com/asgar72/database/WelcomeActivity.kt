package com.asgar72.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val mail = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val userId = intent.getStringExtra(SignInActivity.KEY3)

        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)
        val txtname = findViewById<TextView>(R.id.txtname)
        val txtmail = findViewById<TextView>(R.id.txtmail)
        val txtID = findViewById<TextView>(R.id.txtID)

        txtWelcome.text = "Welcome, $name"
        txtname.text = name
        txtmail.text = mail
        txtID.text = userId
    }
}