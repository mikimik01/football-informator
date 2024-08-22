package com.example.footballmanager.ui.screens.home

import androidx.compose.runtime.Composable
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.home.detail_components.DetailSuccessScreen
import com.example.footballmanager.ui.screens.home.home_components.ErrorScreen
import com.example.footballmanager.ui.screens.home.home_components.LoadingScreen

@Composable
fun DetailScreen() {
    val masterViewModel = Providers.localMasterModelProvider.current as MasterViewModel

    when (val retrievedDataState = masterViewModel.retrievingMatchEventsState) {
        is RetrievingDataState.Success -> {
            DetailSuccessScreen(retrievedDataState.matches)
        }

        is RetrievingDataState.Loading -> LoadingScreen()
        is RetrievingDataState.Error -> ErrorScreen(errorHint = retrievedDataState.errorHint)
    }


}