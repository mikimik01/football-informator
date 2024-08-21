package com.example.footballmanager.ui.screens.register.register_components

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.footballmanager.LoginActivity
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.data_structures.AuthState
import com.example.footballmanager.ui.screens.register.RegisterViewModel
import com.example.footballmanager.ui.screens.register.register_components.register_state_screens.AwaitScreen
import com.example.footballmanager.ui.screens.register.register_components.register_state_screens.BeginScreen

@Preview
@Composable
fun RegisterFields() {
    val registerViewModel = Providers.localRegisterModelProvider.current as RegisterViewModel
    val ctx = LocalContext.current
    val registered by remember { mutableStateOf(registerViewModel.authState) }
    when(registered.value){
        is AuthState.Await -> AwaitScreen()
        is AuthState.Begin -> BeginScreen()
        is AuthState.Error -> BeginScreen((registered.value as AuthState.Error).message)
        is AuthState.Success -> {
            Toast.makeText(ctx, (registered.value as AuthState.Success).message, Toast.LENGTH_SHORT).show()
            ctx.startActivity(Intent(ctx, LoginActivity::class.java))
            (ctx as Activity).finish()
        }
    }
}