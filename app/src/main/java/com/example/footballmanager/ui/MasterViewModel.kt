package com.example.footballmanager.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.network.structures.FixtureDataWrapper
import com.example.footballmanager.R
import com.example.footballmanager.network.FootballApiService
import com.example.footballmanager.ui.theme.navigation.ScreensEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

const val DAYS_OFFSET = 50

@HiltViewModel
class MasterViewModel @Inject constructor(
    private val footballApiService: FootballApiService
) : ViewModel() {
    var retrievingByDateState: RetrievingDataState by mutableStateOf(RetrievingDataState.Loading)
        private set
    var retrievingByLiveNowState: FixtureDataWrapper by mutableStateOf(FixtureDataWrapper())
        private set

    var currentBotNavSelection: ScreensEnum by mutableStateOf(ScreensEnum.Home)

    fun getFixturesLiveNow() {
        viewModelScope.launch {
            runCatching {
                val result = footballApiService.getFixturesByLiveNow()
                retrievingByLiveNowState = result
            }
        }
    }

    fun getFixturesData(date: String = LocalDate.now().toString(), ctx: Context) {
        viewModelScope.launch {
            try {
                val result = footballApiService.getFixturesByDate(date = date)
                if (result.responseBody.size == 0)
                    retrievingByDateState =
                        RetrievingDataState.Error(ctx.getString(R.string.error_hint_limit_reached))
                else
                    retrievingByDateState = RetrievingDataState.Success(result)
            } catch (e: IOException) {
                retrievingByDateState =
                    RetrievingDataState.Error(ctx.getString(R.string.error_hint_internet_connection))
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
    data object Loading : RetrievingDataState
}