package com.example.loginfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mRegisterButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mEmail: EditText = findViewById(R.id.emailEditLogin)
        val mPassword: EditText = findViewById(R.id.passwordEditLogin)
        val mLoginButton: Button = findViewById(R.id.loginButton)
        mRegisterButton = findViewById(R.id.registerLogin)
        val progressBar: ProgressBar = findViewById(R.id.progressBarLogin)


        val mAuth = FirebaseAuth.getInstance()

        mLoginButton.setOnClickListener {
            val email = mEmail.text.toString().trim()
            val password = mPassword.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                mEmail.error = "Email is required"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.error = "Password is required"
            }
            if (password.length < 8) {
                mPassword.error = "Password must be longer 8 characters"
            }
            progressBar.visibility = VISIBLE
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Welcome!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    progressBar.visibility = GONE

                } else {
                    progressBar.visibility = GONE
                    Toast.makeText(
                        this@LoginActivity,
                        task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    fun registerLogin(view: View) {
        mRegisterButton.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
    }
}