package com.example.footballmanager.screens.result_screens

import androidx.compose.runtime.Composable
import com.example.footballmanager.network.structures.FixtureDataWrapper
import com.example.footballmanager.screens.result_screens.success_components.LiveScoreTab
import com.example.footballmanager.screens.result_screens.success_components.MatchScoreTab
import com.example.footballmanager.screens.result_screens.success_components.TabDate

@Composable
fun SuccessScreen(
    fixturesByDate: FixtureDataWrapper,
    fixturesByLiveNow: FixtureDataWrapper //na ten moment nie nikt nic nie gra ->>>>
) {
    TabDate()
    //->>>>> wiec uzyje tutaj meczy z dzisiejsza data
    LiveScoreTab(liveNowFixtures = fixturesByDate)
    MatchScoreTab(fixtureDataWrapper = fixturesByDate)
}