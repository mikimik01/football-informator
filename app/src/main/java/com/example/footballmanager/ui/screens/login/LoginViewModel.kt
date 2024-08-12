package com.example.footballmanager.ui.screens.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.footballmanager.ui.screens.login.firebase.AuthService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    val user: FirebaseUser? get() = authService.getCurrentUser()

    suspend fun signInWithGoogle(idToken: String): Boolean {
        return authService.signInWithGoogle(idToken) != null
    }

    fun getGoogleClient(ctx: Context): Intent {
        return authService.getGoogleClient(ctx = ctx)
    }

    fun logOut() {
        authService.signOut()
    }
}
