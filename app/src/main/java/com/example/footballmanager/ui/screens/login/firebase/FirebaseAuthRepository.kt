package com.example.footballmanager.ui.screens.login.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthService {

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun signInWithGoogle(idToken: String): AuthResult? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return try {
            firebaseAuth.signInWithCredential(credential).await()
        } catch (e: Exception) {
            null
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}