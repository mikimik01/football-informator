package com.example.footballmanager.ui.screens.home_components.success_components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.R
import com.example.footballmanager.ui.screens.home_components.success_components.live_score_components.LiveScoreHeader
import com.example.footballmanager.ui.screens.home_components.success_components.live_score_components.LiveScoreItem
import com.example.footballmanager.ui.MasterViewModel

@Composable
fun LiveScoreTab(
    modifier: Modifier = Modifier
) {
    val masterViewModel: MasterViewModel = hiltViewModel()
    val liveNowMatches by remember {
        mutableStateOf(masterViewModel.retrievingByLiveNowState)
    }
    val liveNowMatchesCount by remember {
        mutableIntStateOf(liveNowMatches.size)
    }
    val lastLiveNowMatchIndex by remember {
        mutableIntStateOf(liveNowMatchesCount - 1)
    }
    val isNotEmpty by remember {
        mutableStateOf((liveNowMatchesCount != 0))
    }
    Toast.makeText(LocalContext.current, isNotEmpty.toString(), Toast.LENGTH_SHORT).show()

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
                        val item = liveNowMatches[i]
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





