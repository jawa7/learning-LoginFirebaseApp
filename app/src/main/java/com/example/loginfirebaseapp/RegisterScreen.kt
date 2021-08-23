package com.example.loginfirebaseapp

import android.graphics.Color.parseColor
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.loginfirebaseapp.components.BottomButtons
import com.example.loginfirebaseapp.components.TopTexts
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(

) {
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(parseColor("#c33764")),
                        Color(parseColor("#1d2671"))
                    )
                )
            )
    ) {
        Column {
            var name by rememberSaveable { mutableStateOf("") }
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var passwordR by rememberSaveable { mutableStateOf("") }

            var errorStateName by remember { mutableStateOf(false) }
            var errorStateEmail by remember { mutableStateOf(false) }
            var errorStatePassword by remember { mutableStateOf(false) }
            var errorStatePasswordR by remember { mutableStateOf(false) }

            var passwordVisibility by remember { mutableStateOf(false) }

            val context = LocalContext.current

            TopTexts(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .weight(0.15f),
                mainText = "Login Firebase App",
                otherText = "Register Your Account!"
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        if (name.isEmpty()) errorStateName = true
                    },
                    label = { Text("Name") },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        focusedLabelColor = Color.Yellow
                    ),
                    leadingIcon = { Icon(Icons.Filled.Person, "") },
                    isError = errorStateName
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        focusedLabelColor = Color.Yellow
                    ),
                    leadingIcon = { Icon(Icons.Filled.Email, "") },
                    isError = errorStateEmail
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        focusedLabelColor = Color.Yellow
                    ),
                    trailingIcon = {
                        val image = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = image,
                                ""
                            )
                        }
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, "")
                    },
                    isError = errorStatePassword
                )
                OutlinedTextField(
                    value = passwordR,
                    onValueChange = { passwordR = it },
                    label = { Text("Confirm Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        focusedLabelColor = Color.Yellow
                    ),
                    trailingIcon = {
                        val image = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = image,
                                ""
                            )
                        }
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, "")
                    },
                    isError = errorStatePasswordR
                )
            }
            BottomButtons(
                mainButton = "Register",
                otherButton = "Login",
                onClickMain = {

                    if (email.isEmpty() || email.length < 5 || !email.contains("@")) errorStateEmail = true
                    if (password.isEmpty() || password.length < 8) errorStatePassword = true
                    if (password != passwordR) errorStatePasswordR = true
                    if (name == "") errorStateName = true
                    if (!errorStateEmail && !errorStatePassword && !errorStateName && !errorStatePasswordR) {
                        mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful && password != password) {
                                    // goes to main screen
                                } else {
                                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                    }

                },
                onClickOther = {
                    //  loginScreen()
                }
            )
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen()
}