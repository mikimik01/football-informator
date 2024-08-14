package com.example.footballmanager.data.network.api

import com.example.footballmanager.data.entities.Match

//sealed interface RetrievingDataState<T> {
//    data class Success<T>(
//        val matches: List<T>,
//        val cached: Boolean
//    ) : RetrievingDataState<T>
//
//    data class Error<T>(
//        val errorHint: String,
//        val matches: List<T> = arrayListOf()
//    ) : RetrievingDataState<T>
//
//    data class Loading<T>(
//        val matches: List<T> = arrayListOf()
//    ) : RetrievingDataState<T>
//}

sealed interface RetrievingDataState<T> {
    data class Success<T>(
        val matches: List<T>,
        val cached: Boolean
    ) : RetrievingDataState<T>

    data class Error<T>(
        val errorHint: String,
        val matches: List<T> = arrayListOf()
    ) : RetrievingDataState<T>

    data class Loading<T>(
        val matches: List<T> = arrayListOf()
    ) : RetrievingDataState<T>
}
