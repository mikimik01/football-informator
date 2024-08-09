package com.example.footballmanager.ui.screens.main

import android.app.Activity
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.footballmanager.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AccountScreen(){
    val ctx = LocalContext.current
    TextButton(onClick = {
        Firebase.auth.signOut()
        ctx.startActivity(Intent(ctx, LoginActivity::class.java))
        val activity = ctx as Activity
        activity.finish()
    }) {
        Text(text = "Log out")
    }
}

@Preview
@Composable
fun AccountPreview(){
    AccountScreen()
}