package com.example.footballmanager.data.network.api

import com.example.footballmanager.data.entities.Match

sealed interface RetrievingDataState {
    data class Success(
        val matches: List<Match>,
        val cached: Boolean
    ) : RetrievingDataState

    data class Error(
        val errorHint: String,
        val matches: List<Match> = arrayListOf()
    ) : RetrievingDataState

    data object Loading : RetrievingDataState
}