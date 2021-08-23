package com.example.loginfirebaseapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
        Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        Button(
            onClick = onClickMain,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
        ) {
            Text(mainButton)
        }
        TextButton(
            onClick = onClickOther,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                otherButton,
                color = Color.Yellow
            )
        }
    }
}