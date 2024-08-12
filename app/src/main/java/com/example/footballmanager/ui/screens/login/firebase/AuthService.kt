package com.example.footballmanager.ui.screens.login.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthService {
    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithGoogle(idToken: String): AuthResult?
    fun signOut()
}
