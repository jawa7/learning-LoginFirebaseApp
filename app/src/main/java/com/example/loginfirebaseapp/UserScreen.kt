package com.example.loginfirebaseapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun UserScreen(
    navController: NavController
) {

    val user = Firebase.auth.currentUser
    Log.d("MainActivity", "UserScreen: ${user!!.email}")
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color(android.graphics.Color.parseColor("#1d2671")),
//                        Color(android.graphics.Color.parseColor("#c33764")),
//                    )
//                )
//            )
//    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(user?.photoUrl),
                contentDescription = null
            )
            user?.displayName?.let {
                Text(
                    text = it,
                    color = Color.Green,
                    style = MaterialTheme.typography.h3
                )
            }
            Text(
                text = user.email!!,
                color = Color.Green,
                style = MaterialTheme.typography.h3
            )
            Text(
                text = user.uid,
                color = Color.Green,
                style = MaterialTheme.typography.h3
            )
        }
    }
// }
