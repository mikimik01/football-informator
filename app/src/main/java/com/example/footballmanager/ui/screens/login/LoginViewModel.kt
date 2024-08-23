package com.example.footballmanager.ui.screens.login

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.data.network.firebase.AuthService
import com.example.footballmanager.ui.screens.data_structures.AuthState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    val user: FirebaseUser? get() = authService.getCurrentUser()

    private val _authState: MutableState<AuthState> = mutableStateOf(AuthState.Begin)
    val authState: State<AuthState> = _authState

    fun getGoogleClient(ctx: Context): Intent {
        return authService.getGoogleClient(ctx = ctx)
    }

    fun signInWithEmailPassword(email: String, password: String, ctx: Context) {
        _authState.value = AuthState.Await
        viewModelScope.launch {
            _authState.value = authService.signInWithEmail(email, password, ctx)
        }
    }

    fun logOut() {
        authService.signOut()
    }
}
