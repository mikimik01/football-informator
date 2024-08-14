package com.example.footballmanager.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.screens.main.home_components.LoadingScreen
import com.example.footballmanager.ui.screens.main.home_components.ErrorScreen
import com.example.footballmanager.ui.screens.main.home_components.SuccessScreen
import retrofit2.http.Header

@Composable
fun HomeScreen(
    retrievingByDateState: RetrievingDataState<Match>,
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