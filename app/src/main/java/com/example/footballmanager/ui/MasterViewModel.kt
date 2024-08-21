package com.example.footballmanager.ui

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.footballmanager.DAYS_OFFSET
import com.example.footballmanager.R
import com.example.footballmanager.data.MatchesDataSource
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.entities.MatchEvent
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.bottom_navigation.AdditionalScreens
import com.example.footballmanager.data.network.firebase.AuthService
import com.example.footballmanager.ui.bottom_navigation.MainScreens
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveItemElements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class MasterViewModel @Inject constructor(
    private val matchesDataSource: MatchesDataSource, private val authService: AuthService
) : ViewModel() {
    var retrievingByDateState: RetrievingDataState<List<Match>> by mutableStateOf(
        RetrievingDataState.Loading()
    )
        private set


    var retrievingByLiveNowState: List<Match> by mutableStateOf(listOf())
        private set

    var currentBotNavSelection: MainScreens by mutableStateOf(MainScreens.Home)

    private val _currentSelectedHeader = mutableStateOf(HeaderType.MainHeader)
    val currentSelectedHeader: State<HeaderType> = _currentSelectedHeader

    private val _selectedDetailItemData =
        mutableStateOf(LiveItemElements("-", 0, ""))//ctx.getString(R.string.score_separator)))
    private val selectedDetailItemData: State<LiveItemElements> = _selectedDetailItemData

    private fun changeDetailViewData(liveItemElements: LiveItemElements) {
        _selectedDetailItemData.value = liveItemElements
    }

    private fun changeHeader(headerType: HeaderType) {
        _currentSelectedHeader.value = headerType
    }

    var retrievingMatchEventsState: RetrievingDataState<DetailScreenData> by mutableStateOf(
        RetrievingDataState.Loading()
    )
        private set

    private fun getMatchEvents(fixtureId: Int, ctx: Context) {
        viewModelScope.launch {
            retrievingMatchEventsState = kotlin.runCatching {
                val fetchResult = matchesDataSource.fetchMatchEvents(fixtureId)
                if (fetchResult.isEmpty()) {
                    RetrievingDataState.Error(errorHint = ctx.getString(R.string.problem_with_fetching_match_events))
                } else {
                    RetrievingDataState.Success(
                        matches = DetailScreenData(
                            matchEvents = fetchResult,
                            fixtureId = fixtureId,
                            selectedItemElements = selectedDetailItemData.value),
                        cached = false)
                }
            }.getOrElse {
                RetrievingDataState.Error(errorHint = ctx.getString(R.string.problem_with_fetching_match_events))
            }
        }
    }

    fun getFixturesLiveNow() {
        viewModelScope.launch {
            runCatching {
                val result = matchesDataSource.fetchMatchesByLiveNow()
                retrievingByLiveNowState = result
            }
        }
    }

    fun navigateToDetailView(navController: NavHostController, liveItemElements: LiveItemElements, ctx: Context) {
        changeDetailViewData(liveItemElements)
        changeHeader(HeaderType.DetailHeader)
        getMatchEvents(fixtureId = liveItemElements.fixtureId, ctx = ctx)
        navController.navigate(
            route = AdditionalScreens.Detail.name
        )

    }

    fun navigateUpToHome(navController: NavHostController){
        changeHeader(HeaderType.MainHeader)
        navController.navigateUp()
    }

    fun navigateToOneOfHomeScreens(navController: NavHostController, screen: MainScreens){
        navController.navigate(screen.name)
        changeHeader(HeaderType.MainHeader)
        currentBotNavSelection = screen
    }

    fun getFixturesData(date: String = LocalDate.now().toString(), ctx: Context) {
        viewModelScope.launch {

            retrievingByDateState = runCatching {
                val fetchResult = matchesDataSource.fetchAndCacheMatchesByDate(date = date)
                if (fetchResult.isEmpty()) {
                    getCached(ctx)
                } else {
                    RetrievingDataState.Success(matches = fetchResult, cached = false)
                }
            }.getOrElse {
                getCached(ctx)
            }
        }
    }

    private suspend fun getCached(ctx: Context): RetrievingDataState<List<Match>> {
        val getCachedResult = matchesDataSource.getCachedMatches()
        return if (getCachedResult.isEmpty()) {
            RetrievingDataState.Error(ctx.getString(R.string.error_hint_internet_connection))
        } else {
            RetrievingDataState.Success(matches = getCachedResult, cached = true)
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

    fun logOut() {
        authService.signOut()
    }

    fun formatDateString(dateString: String, ctx: Context): String {
        val dateTime = OffsetDateTime.parse(dateString)
        val date = dateTime.toLocalDate()
        val today = LocalDate.now()

        return when (date) {
            today -> ctx.getString(R.string.today)
            today.plusDays(1) -> ctx.getString(R.string.tomorrow)
            today.minusDays(1) -> ctx.getString(R.string.yesterday)
            else -> {
                val formatter = DateTimeFormatter.ofPattern(ctx.getString(R.string.date_format))
                date.format(formatter)
            }
        }
    }


}

data class RetrievedData(
    val dayOfWeek: String, val localDate: String, val restOfDate: String
)

data class DetailScreenData(
    val matchEvents: List<MatchEvent>,
    val selectedItemElements: LiveItemElements,
    val fixtureId: Int
)


