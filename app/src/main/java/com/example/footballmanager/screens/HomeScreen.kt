package com.example.footballmanager.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.screens.result_screens.LoadingScreen
import com.example.footballmanager.screens.result_screens.ErrorScreen
import com.example.footballmanager.screens.result_screens.SuccessScreen
import com.example.footballmanager.screens.view_models.RetrievingDataState

@Composable
fun HomeScreen(
    retrievingByDateState: RetrievingDataState,
    //retrievingByLiveNow: RetrievingDataState,
    modifier: Modifier = Modifier
) {
    when (retrievingByDateState) {
        is RetrievingDataState.Success -> SuccessScreen(
            //liveNowFixtures = retrievingByLiveNow,
            fixturesByDate = retrievingByDateState.fixtures)
        is RetrievingDataState.Loading -> LoadingScreen()
        is RetrievingDataState.Error -> ErrorScreen()
    }
}