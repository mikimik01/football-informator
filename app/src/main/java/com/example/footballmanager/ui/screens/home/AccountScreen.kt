package com.example.footballmanager.ui.screens.home

import android.app.Activity
import android.content.Intent
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.LoginActivity
import com.example.footballmanager.R
import com.example.footballmanager.ui.MasterViewModel


@Composable
fun AccountScreen(viewModel: MasterViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    TextButton(onClick = {
        viewModel.logOut()
        ctx.startActivity(Intent(ctx, LoginActivity::class.java))
        (ctx as Activity).finish()
    }) {
        Text(text = stringResource(id = R.string.log_out))
    }
}

@Preview
@Composable
fun AccountPreview(){
    AccountScreen()
}