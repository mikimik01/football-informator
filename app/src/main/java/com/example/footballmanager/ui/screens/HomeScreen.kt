package com.example.footballmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.ui.RetrievingDataState
import com.example.footballmanager.ui.screens.result_screens.LoadingScreen
import com.example.footballmanager.ui.screens.result_screens.ErrorScreen
import com.example.footballmanager.ui.screens.result_screens.SuccessScreen

/*@NavDestination(

)*/
@Composable
fun HomeScreen(
    retrievingByDateState: RetrievingDataState,
    modifier: Modifier = Modifier
) {
    when (retrievingByDateState) {
        is RetrievingDataState.Success -> SuccessScreen(fixturesByDate = retrievingByDateState.fixtures)
        is RetrievingDataState.Loading -> LoadingScreen()
        is RetrievingDataState.Error -> ErrorScreen(errorHint = retrievingByDateState.errorHint)
    }
}