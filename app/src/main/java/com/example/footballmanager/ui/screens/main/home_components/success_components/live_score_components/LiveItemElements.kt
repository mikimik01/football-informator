package com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components

import com.example.footballmanager.R

data class LiveItemElements(
    val defaultValue: String,
    val fixtureId: Int,
    val leagueLogo: String = defaultValue,
    val leagueName: String = defaultValue,
    val nameTeamHome: String = defaultValue,
    val nameTeamAway: String = defaultValue,
    val logoTeamHome: String = defaultValue,
    val logoTeamAway: String = defaultValue,
    val scoreTeamHome: String = defaultValue,
    val scoreTeamAway: String = defaultValue
)