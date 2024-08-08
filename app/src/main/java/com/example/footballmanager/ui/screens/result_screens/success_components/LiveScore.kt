package com.example.footballmanager.ui.screens.result_screens.success_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.R
import com.example.footballmanager.ui.screens.result_screens.success_components.live_score_components.LiveScoreHeader
import com.example.footballmanager.ui.screens.result_screens.success_components.live_score_components.LiveScoreItem
import com.example.footballmanager.ui.MasterViewModel

@Composable
fun LiveScoreTab(
    modifier: Modifier = Modifier
) {
    val homeViewModel: MasterViewModel = hiltViewModel()
    val liveNowFixtures by remember {
        mutableStateOf(homeViewModel.retrievingByLiveNowState)
    }
    val liveNowFixturesCount by remember {
        mutableIntStateOf(liveNowFixtures.responseBody.size)
    }
    val lastLiveNowFixtureIndex by remember {
        mutableIntStateOf(liveNowFixturesCount - 1)
    }
    val isNotEmpty by remember {
        mutableStateOf((liveNowFixturesCount != 0))
    }

    AnimatedVisibility(visible = isNotEmpty) {
        if (isNotEmpty) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.medium),
                    Alignment.Top
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(id = R.dimen.medium)
                    )
            ) {
                LiveScoreHeader()
                val defaultValue = stringResource(id = R.string.default_value)
                LazyRow {
                    for (i in 0..lastLiveNowFixtureIndex) {
                        val item = liveNowFixtures.responseBody[i]
                        with(item) {
                            item {
                                LiveScoreItem(
                                    leagueLogo = league?.logo ?: defaultValue,
                                    leagueName = league?.name ?: defaultValue,
                                    nameTeamHome = teams?.home?.name ?: defaultValue,
                                    nameTeamAway = teams?.away?.name ?: defaultValue,
                                    scoreTeamHome = goals?.home?.toString() ?: defaultValue,
                                    scoreTeamAway = goals?.away?.toString() ?: defaultValue,
                                    logoTeamHome = teams?.home?.logo ?: defaultValue,
                                    logoTeamAway = teams?.away?.logo ?: defaultValue
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}




