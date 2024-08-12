package com.example.footballmanager.ui.screens.main

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CompetitionScreen(){
    Text(text = "Competition")
    TextButton(onClick = {
        throw RuntimeException("Test Crush")
    }) {
        Text(text = "Crash Test")
    }
}

@Preview
@Composable
fun CompetitionPreview(){
    CompetitionScreen()
}