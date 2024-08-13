package com.example.footballmanager.ui.screens.login

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.HomeActivity
import com.example.footballmanager.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    var user by remember { mutableStateOf(viewModel.user) }
    val putExtraName = stringResource(id = R.string.intent_putextra_user_name)
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
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user == null) {
            LogInOutButton(
                onClickFunction = {
                    launcher.launch(viewModel.getGoogleClient(ctx = ctx))
                }, text = stringResource(R.string.sign_in_via_google)
            )
        } else {
            LogInOutButton(
                onClickFunction = {
                    viewModel.logOut()
                    user = null
                }, text = stringResource(R.string.log_out)
            )
        }
    }
}


@Composable
fun LogInOutButton(onClickFunction: () -> Unit, text: String) {
    ElevatedButton(
        onClick = {
            onClickFunction.invoke()
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.login_activity_button_rounded)),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.login_activity_button_height))
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.login_activity_button_padding)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, contentColor = Color.Black
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = stringResource(R.string.google_logo),
            modifier = Modifier.size(dimensionResource(id = R.dimen.login_activity_button_image_size)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.login_activity_button_spacer)))

        Text(
            text,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = dimensionResource(id = R.dimen.login_activity_button_font_size).value.sp,
            letterSpacing = dimensionResource(id = R.dimen.login_activity_button_font_spacing).value.sp
        )
    }
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit, onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account:GoogleSignInAccount? = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}
