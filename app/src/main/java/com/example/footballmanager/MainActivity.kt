package com.example.footballmanager

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.footballmanager.ui.FootballManagerApp
import com.example.footballmanager.ui.theme.FootballManagerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FootballManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FootballManagerApp()
                }
            }
        }
    }
}
