package com.example.footballmanager.ui.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.main.detail_components.DetailSuccessScreen
import com.example.footballmanager.ui.screens.main.home_components.ErrorScreen
import com.example.footballmanager.ui.screens.main.home_components.LoadingScreen

@Composable
fun DetailScreen() {
    val masterViewModel = Providers.localViewModelProvider.current as MasterViewModel

    when (val retrievedDataState = masterViewModel.retrievingMatchEventsState) {
        is RetrievingDataState.Success -> {
            DetailSuccessScreen(retrievedDataState.matches)
        }

        is RetrievingDataState.Loading -> LoadingScreen()
        is RetrievingDataState.Error -> ErrorScreen(errorHint = retrievedDataState.errorHint)
    }


}