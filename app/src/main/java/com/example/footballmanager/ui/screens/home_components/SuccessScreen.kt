package com.example.footballmanager.ui.screens.home_components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.ui.screens.home_components.success_components.LiveScoreTab
import com.example.footballmanager.ui.screens.home_components.success_components.MatchScoreTab
import com.example.footballmanager.ui.screens.home_components.success_components.TabDate

@Composable
fun SuccessScreen(
    matches: List<Match>,
) {
    Column {
        TabDate()
        LiveScoreTab()
        MatchScoreTab(matches = matches)
    }
}