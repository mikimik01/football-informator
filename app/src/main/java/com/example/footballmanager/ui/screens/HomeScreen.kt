package com.example.footballmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.network.RetrievingDataState
import com.example.footballmanager.ui.screens.home_components.LoadingScreen
import com.example.footballmanager.ui.screens.home_components.ErrorScreen
import com.example.footballmanager.ui.screens.home_components.SuccessScreen

@Composable
fun HomeScreen(
    retrievingByDateState: RetrievingDataState,
    retrievedByLiveNowState: List<Match>,
    modifier: Modifier = Modifier
) {
    when (retrievingByDateState) {
        is RetrievingDataState.Success -> SuccessScreen(
            matchesByDate = retrievingByDateState.matches,
            cached = retrievingByDateState.cached,
            matchesLiveNow = retrievedByLiveNowState
        )

        is RetrievingDataState.Loading -> LoadingScreen()
        is RetrievingDataState.Error -> ErrorScreen(errorHint = retrievingByDateState.errorHint)
    }
}