package com.example.footballmanager.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.screens.home.home_components.LoadingScreen
import com.example.footballmanager.ui.screens.home.home_components.ErrorScreen
import com.example.footballmanager.ui.screens.home.home_components.SuccessScreen

@Composable
fun HomeScreen(
    retrievingByDateState: RetrievingDataState<List<Match>>,
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