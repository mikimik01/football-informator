package com.example.footballmanager.ui.screens.register.register_components.register_state_screens

import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.footballmanager.R
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.register.RegisterViewModel

@Preview
@Composable
fun BeginScreen(errorMessage: String? = null) {

    val blank = stringResource(id = R.string.blank)
    val registerViewModel = Providers.localRegisterModelProvider.current as RegisterViewModel
    val ctx = LocalContext.current
    var email by remember { mutableStateOf(blank) }
    var password by remember { mutableStateOf(blank) }
    var repeatPassword by remember { mutableStateOf(blank) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRepeatPasswordVisible by remember { mutableStateOf(false) }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val btnColors = ButtonColors(
        contentColor = Color.White,
        containerColor = colorResource(id = R.color.app_red_motive),
        disabledContentColor = colorResource(id = R.color.app_grey_motive),
        disabledContainerColor = colorResource(id = R.color.app_darker_white_motive),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.begin_screen_column_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.email)) },
            placeholder = { Text(stringResource(id = R.string.enter_your_email)) },
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.app_white_motive)),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.begin_screen_spacer)))

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password)) },
            placeholder = { Text(stringResource(id = R.string.enter_your_password)) },
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
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onDone = {
            })
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.begin_screen_spacer)))

        TextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = { Text(stringResource(R.string.repeat_password)) },
            placeholder = { Text(stringResource(R.string.enter_your_password_again)) },
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.app_white_motive)),
            visualTransformation = if (isRepeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (isRepeatPasswordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                IconButton(onClick = { isRepeatPasswordVisible = !isRepeatPasswordVisible }) {
                    Icon(painterResource(id = image), contentDescription = null)
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
            })
        )

        Text(
            text = errorMessage?:blank,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(top = dimensionResource(id = R.dimen.begin_screen_text_padding))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.begin_screen_spacer)))

        Button(
            onClick = {
                registerViewModel.registerWithEmailPassword(
                    email = email,
                    password = password,
                    password1 = repeatPassword,
                    ctx = ctx
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = btnColors
        ) {
            Text(stringResource(R.string.create_account))
        }
        Button(
            onClick = { backDispatcher?.onBackPressed() },
            modifier = Modifier.fillMaxWidth(),
            colors = btnColors
        ) {
            Text(stringResource(R.string.back))
        }
    }
}