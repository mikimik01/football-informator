package com.example.footballmanager.data.network.firebase

import android.content.Context
import android.content.Intent
import com.example.footballmanager.ui.screens.data_structures.AuthState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthService {
    fun getGoogleClient(ctx: Context): Intent
    suspend fun registerWithEmail(email: String, password: String, password1: String, ctx: Context): AuthState
    suspend fun signInWithEmail(email: String, password: String, ctx: Context): AuthState
    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithGoogle(idToken: String): AuthResult?
    fun signOut()
}
