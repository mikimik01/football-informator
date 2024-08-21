package com.example.footballmanager.ui.screens.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.register.register_components.RegisterFields

@Preview
@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->


        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .align(alignment = Alignment.Center)
            ) {
                with(Providers) {
                    CompositionLocalProvider(
                        localRegisterModelProvider provides registerViewModel,
                    ) {
                        RegisterFields()
                    }
                }

            }
        }
    }
}
