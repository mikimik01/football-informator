package com.example.footballmanager.ui.screens.data_structures

import com.example.footballmanager.data.entities.MatchEvent
import com.example.footballmanager.ui.screens.home.home_components.success_components.live_score_components.LiveItemElements

data class DetailScreenData(
    val matchEvents: List<MatchEvent>,
    val selectedItemElements: LiveItemElements,
    val fixtureId: Int
)
