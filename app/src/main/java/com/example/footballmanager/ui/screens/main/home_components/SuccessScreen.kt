package com.example.footballmanager.ui.screens.main.home_components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.screens.main.home_components.success_components.LiveScoreTab
import com.example.footballmanager.ui.screens.main.home_components.success_components.MatchScoreTab
import com.example.footballmanager.ui.screens.main.home_components.success_components.TabDate
import retrofit2.http.Header

@Composable
fun SuccessScreen(
    matchesByDate: List<Match>,
    matchesLiveNow: List<Match>,
    cached: Boolean
) {
    Column {
        TabDate()
        LiveScoreTab(matches = matchesLiveNow)
        MatchScoreTab(matches = matchesByDate, cached = cached)
    }
}