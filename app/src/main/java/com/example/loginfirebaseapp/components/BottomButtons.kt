package com.example.loginfirebaseapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtons(
    mainButton: String,
    otherButton: String,
    onClickMain: () -> Unit,
    onClickOther: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 18.dp)
            .fillMaxSize()
    ) {
        Button(
            onClick = onClickMain,
            modifier = Modifier
                .fillMaxWidth(0.6f)
        ) {
            Text(mainButton)
        }
        TextButton(
            onClick = onClickOther,
            modifier = Modifier
        ) {
            Text(
                otherButton,
                color = Color.Yellow
            )
        }
    }
}