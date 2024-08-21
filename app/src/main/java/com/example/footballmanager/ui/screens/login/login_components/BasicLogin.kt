package com.example.footballmanager.ui.screens.login.login_components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R
import com.example.footballmanager.RegisterActivity


@Composable
fun BasicLogin(onLoginClick: (String, String, Context) -> Unit, errorMessage: String? = null) {
    val ctx = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val btnColors = ButtonColors(
        contentColor = Color.White,
        containerColor = colorResource(id = R.color.app_red_motive),
        disabledContentColor = colorResource(id = R.color.app_grey_motive),
        disabledContainerColor = colorResource(id = R.color.app_darker_white_motive),
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.app_white_motive)),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )



        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.app_white_motive)),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (isPasswordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(painterResource(id = image), contentDescription = null)
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onLoginClick(email, password, ctx)
            })
        )

        Text(
            text = errorMessage?:"",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Login Button
        Button(
            onClick = { onLoginClick(email, password, ctx) },
            modifier = Modifier.fillMaxWidth(),
            colors = btnColors
        ) {
            Text("Login")
        }

        Button(
            onClick = {
                ctx.startActivity(Intent(ctx, RegisterActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = btnColors
        ) {
            Text("Register")
        }
    }
}
