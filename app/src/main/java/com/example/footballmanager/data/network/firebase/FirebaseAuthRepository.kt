package com.example.footballmanager.data.network.firebase

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.footballmanager.R
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
        context: Context
    ): AuthState {
        if (password1 != password)
            return AuthState.Error(context.getString(R.string.passwords_are_not_matching))
        if (password.isBlank())
            return AuthState.Error(context.getString(R.string.password_cannot_be_blank))

        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context as Activity) { task ->
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
                                        continuation.resume(AuthState.Success(context.getString(R.string.registered_successfully)))
                                    } else {
                                        continuation.resume(AuthState.Error(context.getString(R.string.failed_to_set_username)))
                                    }
                                } else {
                                    continuation.resume(AuthState.Error(context.getString(R.string.failed_to_set_username)))
                                }
                            }
                    } else {
                        val errorState = try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            AuthState.Error(context.getString(R.string.weak_password))
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            AuthState.Error(context.getString(R.string.invalid_email))
                        } catch (e: FirebaseAuthUserCollisionException) {
                            AuthState.Error(context.getString(R.string.user_already_exists))
                        } catch (e: Exception) {
                            AuthState.Error(
                                context.getString(
                                    R.string.registration_failed,
                                    e.message
                                ))
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
            AuthState.Error(ctx.getString(R.string.no_account_found_with_this_email_address))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            AuthState.Error(ctx.getString(R.string.invalid_email_address_or_password))
        } catch (e: FirebaseAuthUserCollisionException) {
            AuthState.Error(ctx.getString(R.string.an_account_with_this_email_already_exists))
        } catch (e: FirebaseAuthException) {
            AuthState.Error(ctx.getString(R.string.authentication_failed, e.localizedMessage))
        } catch (e: IOException) {
            AuthState.Error(ctx.getString(R.string.network_error_please_check_your_internet_connection))
        } catch (e: Exception) {
            AuthState.Error(ctx.getString(R.string.unknown_error_occurred))
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