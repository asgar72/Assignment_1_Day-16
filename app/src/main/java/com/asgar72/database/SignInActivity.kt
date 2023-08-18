package com.asgar72.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    companion object {
        const val KEY1 = "com.asgar72.database.SignInActivity.mail"
        const val KEY2 = "com.asgar72.database.SignInActivity.name"
        const val KEY3 = "com.asgar72.database.SignInActivity.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val txtsignup = findViewById<TextView>(R.id.txtsignup)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<EditText>(R.id.etUserName)

        txtsignup.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignIn.setOnClickListener {
            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)
            } else {
                Toast.makeText(this, "Please enter user Id", Toast.LENGTH_SHORT).show()
            }
        }
    } //onCreate method is over

    private fun readData(uniqueId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            //if user exist or not
            if (it.exists()) {
                //Welcome user in your app, with intent and also pass data
                val email = it.child("email").value
                val name = it.child("name").value
                val uniqueId = it.child("uniqueId").value

                val intentWelcome = Intent(this, WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, uniqueId.toString())
                startActivity(intentWelcome)
            } else {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "User does not exist! Please signUp ", Toast.LENGTH_SHORT).show()
        }
    }
}