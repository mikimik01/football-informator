package com.example.footballmanager.ui.screens.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.footballmanager.data.network.firebase.AuthService
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    val user: FirebaseUser? get() = authService.getCurrentUser()

    fun getGoogleClient(ctx: Context): Intent {
        return authService.getGoogleClient(ctx = ctx)
    }

    fun logOut() {
        authService.signOut()
    }
}
