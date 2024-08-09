package com.example.footballmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.footballmanager.ui.screens.login.LoginScreen
import com.example.footballmanager.ui.theme.FootballManagerTheme
import com.google.android.gms.auth.api.identity.BeginSignInResult
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballManagerTheme {
                LoginScreen()
            }
        }
    }
}