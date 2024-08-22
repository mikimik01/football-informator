package com.example.footballmanager.ui.screens.home.home_components.success_components.live_score_components

data class LiveItemElements(
    val defaultValue: String,
    val fixtureId: Int,
    val date: String,
    val leagueLogo: String = defaultValue,
    val leagueName: String = defaultValue,
    val nameTeamHome: String = defaultValue,
    val nameTeamAway: String = defaultValue,
    val logoTeamHome: String = defaultValue,
    val logoTeamAway: String = defaultValue,
    val scoreTeamHome: String = defaultValue,
    val scoreTeamAway: String = defaultValue
)