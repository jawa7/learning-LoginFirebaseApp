package com.example.loginfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var mLoginText: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val mEmail: EditText = findViewById(R.id.emailEditLogin)
        val mPassword: EditText = findViewById(R.id.passwordEditLogin)
        val mPasswordR: EditText = findViewById(R.id.passwordRepeatEdit)
        val mRegisterButton: Button = findViewById(R.id.loginButton)
        mLoginText = findViewById(R.id.loginText)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        mAuth = FirebaseAuth.getInstance()


        if (mAuth.currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        mRegisterButton.setOnClickListener {
            val email = mEmail.text.toString().trim()
            val password = mPassword.text.toString().trim()
            val passwordR = mPasswordR.text.toString().trim()

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

            if (password != passwordR) {
                mPasswordR.error = "Passwords are not similar"
            }

            progressBar.visibility = VISIBLE

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful && password != passwordR) {
                    progressBar.visibility = INVISIBLE
                    Toast.makeText(this@RegisterActivity, "User Created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    progressBar.visibility = INVISIBLE

                    Toast.makeText(
                        this@RegisterActivity,
                        task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun loginText(view: View) {
        mLoginText.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }


}