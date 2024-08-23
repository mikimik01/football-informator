package com.example.footballmanager.ui.screens.login

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.HomeActivity
import com.example.footballmanager.LoginActivity
import com.example.footballmanager.R
import com.example.footballmanager.ui.screens.data_structures.AuthState
import com.example.footballmanager.ui.screens.login.login_components.BasicLogin
import com.example.footballmanager.ui.screens.login.login_components.LogInOutButton
import com.example.footballmanager.ui.screens.login.login_components.rememberFirebaseAuthLauncher
import com.example.footballmanager.ui.theme.background
import kotlin.math.round

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    var user by remember { mutableStateOf(loginViewModel.user) }
    val putExtraName = stringResource(id = R.string.intent_putextra_user_name)
    val logged by remember { mutableStateOf(loginViewModel.authState) }
    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
        user = result.user
        val intent = Intent(ctx, HomeActivity::class.java)
        intent.putExtra(putExtraName, user?.displayName.toString())
        ctx.startActivity(intent)
        val activity = ctx as? Activity
        activity!!.finish()
    }, onAuthError = {
        user = null
    })



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
                    .padding(dimensionResource(id = R.dimen.login_image_padding))
                    .size(dimensionResource(id = R.dimen.login_image_size))
                    .clip(CircleShape)
            )
            Row(
                Modifier.align(alignment = Alignment.Center)) {

                when (logged.value){
                    is AuthState.Await, AuthState.Begin -> {
                        BasicLogin ({ email, password, ctx ->
                            loginViewModel.signInWithEmailPassword(email, password, ctx)
                        })
                    }
                    is AuthState.Error -> {
                        BasicLogin ({ email, password, ctx ->
                            loginViewModel.signInWithEmailPassword(email, password, ctx)
                        },
                            errorMessage = (logged.value as AuthState.Error).message
                        )
                    }
                    is AuthState.Success -> {
                        Toast.makeText(ctx, (logged.value as AuthState.Success).message, Toast.LENGTH_SHORT).show()
                        ctx.startActivity(Intent(ctx, LoginActivity::class.java))
                        (ctx as Activity).finish()
                    }
                }
            }

            Row(
                Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(dimensionResource(id = R.dimen.login_row_padding))
                    .padding(bottom = dimensionResource(id = R.dimen.login_row_padding))
            ) {
                if (user == null) {
                    LogInOutButton(
                        onClickFunction = {
                            launcher.launch(loginViewModel.getGoogleClient(ctx = ctx))
                        }, text = stringResource(R.string.sign_in_via_google)
                    )
                } else {
                    LogInOutButton(
                        onClickFunction = {
                            loginViewModel.logOut()
                            user = null
                        }, text = stringResource(R.string.log_out)
                    )
                }
            }
        }
    }

}