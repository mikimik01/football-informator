package com.example.footballmanager.ui.screens.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballmanager.R
import com.example.footballmanager.ui.screens.login.login_components.BasicLogin
import com.example.footballmanager.ui.screens.register.register_components.RegisterFields

@Preview
@Composable
fun RegisterScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->


        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Ball icon",
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .padding(50.dp)
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Row(
                Modifier.align(alignment = Alignment.Center)
            ) {
                RegisterFields()
            }
        }
    }
}
