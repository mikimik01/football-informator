package com.example.footballmanager.ui.screens.main.detail_components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.footballmanager.data.MatchesDataSource
import com.example.footballmanager.data.entities.MatchEvents
import com.example.footballmanager.data.network.api.RetrievingDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val matchesDataSource: MatchesDataSource
) : ViewModel() {
    var retrievingDataState: RetrievingDataState<MatchEvents> by mutableStateOf(RetrievingDataState.Loading())
        private set

    fun getMatchEvents(fixtureId: Int){

    }
}