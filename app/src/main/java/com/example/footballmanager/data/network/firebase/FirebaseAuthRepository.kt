package com.example.footballmanager.data.network.firebase

import android.content.Context
import android.content.Intent
import com.example.footballmanager.data.modules.GoogleToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @GoogleToken private val googleToken: String
) : AuthService {
    override fun getGoogleClient(ctx: Context): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleToken).requestEmail().build()
        val googleSignInClient = GoogleSignIn.getClient(ctx, gso)
        return googleSignInClient.signInIntent
    }

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