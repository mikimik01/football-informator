package com.example.footballmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.footballmanager.ui.FootballManagerApp
import com.example.footballmanager.ui.theme.FootballManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userName = intent.getStringExtra("name")?:"-"
        enableEdgeToEdge()
        setContent {
            FootballManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FootballManagerApp(userName = userName)
                }
            }
        }
    }
}
