package com.example.footballmanager.ui.screens.register

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.data.network.firebase.AuthService
import com.example.footballmanager.ui.screens.data_structures.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _authState:MutableState<AuthState> = mutableStateOf(AuthState.Begin)
    val authState: State<AuthState> = _authState

    fun registerWithEmailPassword(
        email: String, password: String, password1: String, ctx: Context
    ) {
        _authState.value = AuthState.Await
        viewModelScope.launch {
            _authState.value = authService.registerWithEmail(email, password, password1, ctx)
        }
    }
}
