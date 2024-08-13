package com.example.footballmanager.ui.screens.login.firebase

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthService {
    fun getGoogleClient(ctx: Context): Intent
    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithGoogle(idToken: String): AuthResult?
    fun signOut()
}
