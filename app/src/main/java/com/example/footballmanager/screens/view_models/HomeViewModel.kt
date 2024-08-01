package com.example.footballmanager.screens.view_models

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.R
import com.example.footballmanager.network.FixtureDataWrapper
import com.example.footballmanager.network.FootballApi
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

const val DAYS_OFFSET = 50

class HomeViewModel : ViewModel() {

    var retrievingDataState: RetrievingDataState by mutableStateOf(RetrievingDataState.Loading)
        private set

    fun getFixturesData(date: String = LocalDate.now().toString(), ctx:Context) {
        viewModelScope.launch {
            try {
                val result = FootballApi.retrofitService.getFixtures(date = date)
                if (result.responseBody.size == 0)
                    retrievingDataState = RetrievingDataState.Error(ctx.getString(R.string.error_hint_limit_reached))
                else
                    retrievingDataState = RetrievingDataState.Success(result)
            } catch (e: IOException) {
                retrievingDataState = RetrievingDataState.Error(ctx.getString(R.string.error_hint_internet_connection))
            }
        }
    }

    private fun getDateProperties(currentTime: LocalDate): RetrievedData {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val month = currentTime.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val dayOfWeek = currentTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val day = currentTime.format(formatter)
        val fullyFormattedDate = "$day $month"
        return RetrievedData(dayOfWeek, currentTime.toString(), fullyFormattedDate)
    }


    fun get7DatesToDisplay(): List<RetrievedData> {
        val retList = mutableListOf<RetrievedData>()
        for (daysOffset in -DAYS_OFFSET..DAYS_OFFSET) {
            val currentTime = LocalDate.now().plusDays(daysOffset.toLong())
            retList.add(getDateProperties(currentTime))
        }
        return retList
    }

    fun getOneMoreDay(whichDay: Int): RetrievedData {
        val currentTime = LocalDate.now().plusDays((DAYS_OFFSET + whichDay).toLong())
        return getDateProperties(currentTime)
    }
}

data class RetrievedData(
    val dayOfWeek: String, val localDate: String, val restOfDate: String
)


sealed interface RetrievingDataState {
    data class Success(val fixtures: FixtureDataWrapper) : RetrievingDataState
    data class Error(val errorHint: String) : RetrievingDataState
    object Loading : RetrievingDataState
}