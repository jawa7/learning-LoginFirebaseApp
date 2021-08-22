package com.example.loginfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Logout(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
}