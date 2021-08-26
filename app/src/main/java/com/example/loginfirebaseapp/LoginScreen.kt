package com.example.loginfirebaseapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginfirebaseapp.components.BottomButtons
import com.example.loginfirebaseapp.components.TopTexts
import com.google.firebase.auth.FirebaseAuth

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    navController: NavController
) {

    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#c33764")),
                        Color(android.graphics.Color.parseColor("#1d2671"))
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            var errorStateEmail by remember { mutableStateOf(false) }
            var errorStatePassword by remember { mutableStateOf(false) }

            var passwordVisibility by remember { mutableStateOf(false) }

            val context = LocalContext.current

            val (focusRequester) = FocusRequester.createRefs()
            val keyboardController = LocalSoftwareKeyboardController.current

            TopTexts(
                mainText = "Login Firebase App",
                otherText = "Log Into Your Account!"
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        focusedLabelColor = Color.Yellow
                    ),
                    leadingIcon = { Icon(Icons.Filled.Email, "") },
                    isError = errorStateEmail,
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester.requestFocus() }
                    ),
                    modifier = Modifier.focusRequester(focusRequester)

                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedBorderColor = Color.Yellow,
                        focusedLabelColor = Color.Yellow
                    ),
                    trailingIcon = {
                        val image =
                            if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
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
                    isError = errorStatePassword,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    modifier = Modifier.focusRequester(focusRequester)

                )
            }
            BottomButtons(
                mainButton = "Login",
                otherButton = "Signup",
                onClickMain = {
                    if (email.isEmpty() || email.length < 5 || !email.contains("@")) errorStateEmail =
                        true
                    if (password.isEmpty() || password.length < 8) errorStatePassword = true

                    if (!errorStateEmail && !errorStatePassword) {
                        mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate("user_screen")
                        } else {
                            Toast.makeText(
                                context,
                                task.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                            }
                    }
                },
                onClickOther = {
                    navController.popBackStack(
                        "register_screen",
                        inclusive = false
                    )
                }
            )
        }
    }
}
