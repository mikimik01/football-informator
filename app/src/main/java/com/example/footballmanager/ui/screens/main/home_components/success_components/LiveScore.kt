package com.example.footballmanager.ui.screens.main.home_components.success_components

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
import com.example.footballmanager.R
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveItemElements
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveScoreHeader
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveScoreItem

@Composable
fun LiveScoreTab(
    matches: List<Match>,
    modifier: Modifier = Modifier
) {
    val liveNowMatchesCount by remember {
        mutableIntStateOf(matches.size)
    }
    val lastLiveNowMatchIndex by remember {
        mutableIntStateOf(liveNowMatchesCount - 1)
    }
    val isNotEmpty by remember {
        mutableStateOf((liveNowMatchesCount != 0))
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
                    for (i in 0..lastLiveNowMatchIndex) {
                        val item = matches[i]
                        with(item) {
                            item {
                                LiveScoreItem(
                                    LiveItemElements(
                                        defaultValue = stringResource(id = R.string.score_separator),
                                        leagueLogo = league?.logo ?: defaultValue,
                                        leagueName = league?.name ?: defaultValue,
                                        nameTeamHome = teams?.home?.name ?: defaultValue,
                                        nameTeamAway = teams?.away?.name ?: defaultValue,
                                        scoreTeamHome = goals?.home?.toString() ?: defaultValue,
                                        scoreTeamAway = goals?.away?.toString() ?: defaultValue,
                                        logoTeamHome = teams?.home?.logo ?: defaultValue,
                                        logoTeamAway = teams?.away?.logo ?: defaultValue
                                    )
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}





