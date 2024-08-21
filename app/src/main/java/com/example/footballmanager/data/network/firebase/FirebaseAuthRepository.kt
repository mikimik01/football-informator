package com.example.footballmanager.data.network.firebase

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.footballmanager.data.modules.GoogleToken
import com.example.footballmanager.ui.screens.data_structures.AuthState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    override suspend fun registerWithEmail(
        email: String,
        password: String,
        password1: String,
        ctx: Context
    ): AuthState {
        if (password1 != password)
            return AuthState.Error("Passwords are not matching.")
        if (password.isBlank())
            return AuthState.Error("Password cannot be blank.")

        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx as Activity) { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        val profileUpdates = userProfileChangeRequest {
                            displayName = email
                        }

                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { profileUpdateTask ->
                                if (profileUpdateTask.isSuccessful) {
                                    val updatedUser = firebaseAuth.currentUser
                                    if (updatedUser?.displayName != null) {
                                        continuation.resume(AuthState.Success("Registered successfully."))
                                    } else {
                                        continuation.resume(AuthState.Error("Failed to set username."))
                                    }
                                } else {
                                    continuation.resume(AuthState.Error("Failed to set username."))
                                }
                            }
                    } else {
                        val errorState = try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            AuthState.Error("Weak Password.")
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            AuthState.Error("Invalid Email.")
                        } catch (e: FirebaseAuthUserCollisionException) {
                            AuthState.Error("User Already Exists.")
                        } catch (e: Exception) {
                            AuthState.Error("Registration Failed: ${e.message}")
                        }
                        continuation.resume(errorState)
                    }
                }
        }
    }


    override suspend fun signInWithEmail(
        email: String,
        password: String,
        ctx: Context
    ): AuthState {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthState.Success(result.toString())
        } catch (e: FirebaseAuthInvalidUserException) {
            AuthState.Error("No account found with this email address.")
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            AuthState.Error("Invalid email address or password.")
        } catch (e: FirebaseAuthUserCollisionException) {
            AuthState.Error("An account with this email already exists.")
        } catch (e: FirebaseAuthException) {
            AuthState.Error("Authentication failed: ${e.localizedMessage}")
        } catch (e: IOException) {
            AuthState.Error("Network error. Please check your internet connection.")
        } catch (e: Exception) {
            AuthState.Error("Unknown error occurred: ${e.localizedMessage}")
        }
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