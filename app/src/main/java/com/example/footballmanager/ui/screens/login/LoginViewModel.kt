package com.example.footballmanager.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.footballmanager.ui.screens.login.firebase.AuthService
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

    fun logOut() {
        authService.signOut()
    }
}
