package com.example.footballmanager.screens.view_models

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.network.FixtureDataWrapper
import com.example.footballmanager.network.FootballApi
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HomeViewModel : ViewModel() {
    var retrievingDataState: RetrievingDataState by mutableStateOf(RetrievingDataState.Loading)
        private set

    //fun get
    fun getFixturesData(date: String = LocalDate.now().toString()) {
        viewModelScope.launch {
            try {
                val result = FootballApi.retrofitService.getFixtures(date = date)
                retrievingDataState = RetrievingDataState.Success(result)
            } catch (e: IOException) {
                retrievingDataState = RetrievingDataState.Error
                Log.d("Errors_", "getFixturesData: $e")
                //Toast.makeText(ctx, "Check \"Errors_\"", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun get7DatesToDisplay(): List<RetrievedData> {
        val retList = mutableListOf<RetrievedData>()
        for (daysOffset in -50..50) {
            val currentTime = LocalDate.now().plusDays(daysOffset.toLong())
            val formatter = DateTimeFormatter.ofPattern("dd")
            val month = currentTime.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
            val dayOfWeek = currentTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
            val day = currentTime.format(formatter)
            val fullyFormattedDate = "$day $month"
            retList.add(RetrievedData(dayOfWeek, currentTime.toString(), fullyFormattedDate))
        }
        return retList
    }

    fun getOneMoreDay(whichDay: Int): RetrievedData {
        val currentTime = LocalDate.now().plusDays((4 + whichDay).toLong())
        val formatter = DateTimeFormatter.ofPattern("dd")
        val month = currentTime.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val dayOfWeek = currentTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val day = currentTime.format(formatter)
        val fullyFormattedDate = "$day $month"
        return RetrievedData(dayOfWeek, currentTime.toString(), fullyFormattedDate)
    }
}


data class RetrievedData(
    val dayOfWeek: String, val localDate: String, val restOfDate: String
)


sealed interface RetrievingDataState {
    data class Success(val fixtures: FixtureDataWrapper) : RetrievingDataState
    object Error : RetrievingDataState
    object Loading : RetrievingDataState
}