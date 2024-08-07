package com.example.footballmanager.data.network

import com.example.footballmanager.data.entities.Match

sealed interface RetrievingDataState {
    data class Success(val matches: List<Match>) : RetrievingDataState
    data class Error(
        val errorHint: String,
        val matches: List<Match> = arrayListOf()
    ) : RetrievingDataState

    data object Loading : RetrievingDataState
}