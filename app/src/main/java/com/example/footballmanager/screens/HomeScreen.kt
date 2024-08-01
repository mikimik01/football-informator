package com.example.footballmanager.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.screens.result_screens.ErrorScreen
import com.example.footballmanager.screens.result_screens.LoadingScreen
import com.example.footballmanager.screens.result_screens.SuccessScreen
import com.example.footballmanager.screens.view_models.RetrievingDataState

@Composable
fun HomeScreen(
    retrievingDataState: RetrievingDataState,
    modifier: Modifier = Modifier
) {
    when (retrievingDataState) {
        is RetrievingDataState.Success -> SuccessScreen(fixture = retrievingDataState.fixtures)
        is RetrievingDataState.Loading -> LoadingScreen()
        is RetrievingDataState.Error -> ErrorScreen()
    }
}