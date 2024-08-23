package com.example.footballmanager.data.network.api

sealed interface RetrievingDataState<T> {
    data class Success<T>(
        val matches: T,
        val cached: Boolean
    ) : RetrievingDataState<T>

    data class Error<T>(
        val errorHint: String,
        val matches: T? = null
    ) : RetrievingDataState<T>

    data class Loading<T>(
        val matches: T? = null
    ) : RetrievingDataState<T>
}
