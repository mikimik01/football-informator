package com.example.footballmanager.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.DAYS_OFFSET
import com.example.footballmanager.R
import com.example.footballmanager.data.MatchesRepository
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.network.RetrievingDataState
import com.example.footballmanager.ui.theme.navigation.ScreensEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class MasterViewModel @Inject constructor(
    private val matchesRepository: MatchesRepository
) : ViewModel() {
    var retrievingByDateState: RetrievingDataState by mutableStateOf(RetrievingDataState.Loading)
        private set
    var retrievingByLiveNowState: List<Match> by mutableStateOf(arrayListOf())
        private set

    var currentBotNavSelection: ScreensEnum by mutableStateOf(ScreensEnum.Home)

    fun getFixturesLiveNow() {
        viewModelScope.launch {
            runCatching {
                val result = matchesRepository.fetchMatchesByLiveNow()
                retrievingByLiveNowState = result
            }
        }
    }

    fun getFixturesData(date: String = LocalDate.now().toString(), ctx: Context) {
        Log.d("mikolimikoli", "Launches")
        viewModelScope.launch {
            try {
                val fetchResult = matchesRepository.fetchAndCacheMatchesByDate(date = date)
                retrievingByDateState = RetrievingDataState.Success(matches = fetchResult, cached = false)
            } catch (e: Exception) {

                val getCachedResult = matchesRepository.getCachedMatches()
                if (getCachedResult.isEmpty()) {
                    retrievingByDateState =
                        RetrievingDataState.Error(ctx.getString(R.string.error_hint_limit_reached))
                } else {
                    retrievingByDateState = RetrievingDataState.Success(matches = getCachedResult, cached = true)
                }
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



