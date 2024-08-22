package com.example.footballmanager.ui.screens.register

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.LoginActivity
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.data_structures.AuthState
import com.example.footballmanager.ui.screens.register.register_components.AwaitScreen
import com.example.footballmanager.ui.screens.register.register_components.BeginScreen

@Preview
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                Modifier.align(alignment = Alignment.Center)
            ) {
                with(Providers) {
                    CompositionLocalProvider(
                        localRegisterModelProvider provides registerViewModel,
                    ) {
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
                }
            }
        }
    }
}
