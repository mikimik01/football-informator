package com.example.footballmanager.ui.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

object Providers {
    val localViewModelProvider = compositionLocalOf<ViewModel?> { null }
    val localNavControllerProvider = compositionLocalOf<NavHostController?> { null }
}