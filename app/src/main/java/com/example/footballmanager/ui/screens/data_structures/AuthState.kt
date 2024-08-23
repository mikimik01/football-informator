package com.example.footballmanager.ui.screens.data_structures


sealed interface AuthState {
    data object Begin: AuthState
    data object Await: AuthState
    data class Error(val message: String): AuthState
    data class Success(val message: String): AuthState
}