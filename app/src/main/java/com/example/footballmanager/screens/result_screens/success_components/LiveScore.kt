package com.example.footballmanager.screens.result_screens.success_components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.footballmanager.R
import com.example.footballmanager.screens.view_models.HomeViewModel

@Composable
fun LiveScoreTab(
    modifier: Modifier = Modifier
) {
    val homeViewModel: HomeViewModel = viewModel()
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
                            val leagueName = league?.name ?: defaultValue
                            val leagueLogo = league?.logo ?: defaultValue
                            val nameTeamHome = teams?.home?.name ?: defaultValue
                            val nameTeamAway = teams?.away?.name ?: defaultValue
                            val scoreTeamHome = goals?.home?.toString() ?: defaultValue
                            val scoreTeamAway = goals?.away?.toString() ?: defaultValue
                            val logoTeamHome = teams?.home?.logo ?: defaultValue
                            val logoTeamAway = teams?.away?.logo ?: defaultValue
                            item {
                                LiveScoreItem(
                                    leagueLogo = leagueLogo,
                                    leagueName = leagueName,
                                    nameTeamHome = nameTeamHome,
                                    nameTeamAway = nameTeamAway,
                                    scoreTeamHome = scoreTeamHome,
                                    scoreTeamAway = scoreTeamAway,
                                    logoTeamHome = logoTeamHome,
                                    logoTeamAway = logoTeamAway
                                )
                            }
                        }
                    }
                }

            }
        }
    }

    val ctx = LocalContext.current
    Toast.makeText(ctx, liveNowFixtures.responseBody.size.toString(), Toast.LENGTH_SHORT).show()
}


@Composable
fun LiveScoreHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.big))
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.live_now),
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 1.56.em,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = stringResource(R.string.see_more),
            color = colorResource(id = R.color.app_red_motive),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun LiveScoreItem(
    modifier: Modifier = Modifier,
    leagueLogo: String,
    leagueName: String,
    nameTeamHome: String,
    nameTeamAway: String,
    scoreTeamHome: String,
    scoreTeamAway: String,
    logoTeamHome: String,
    logoTeamAway: String
) {
    Box(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.big))) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.medium),
                Alignment.Start
            ),
            modifier = Modifier
                .requiredWidth(width = dimensionResource(id = R.dimen.live_score_item_width))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.medium),
                    Alignment.CenterVertically
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.live_score_item_corners)))
                    .background(color = colorResource(id = R.color.highlighted_element_color))
                    .padding(all = dimensionResource(id = R.dimen.medium))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.live_score_item_spacing), Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(leagueLogo),
                            contentDescription = "image 6",
                            modifier = Modifier
                                .requiredSize(size = dimensionResource(id = R.dimen.live_score_item_image_size))
                                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium)))
                        )
                        Text(
                            text = leagueName,
                            color = colorResource(id = R.color.app_darker_white_motive),
                            textAlign = TextAlign.Center,
                            lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
                            style = TextStyle(
                                fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    LeagueMatchesLiveShower()
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.medium),
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TeamField(
                        teamName = nameTeamHome,
                        teamLogo = logoTeamHome
                    )
                    Text(
                        text = "$scoreTeamHome ${stringResource(id = R.string.score_separator)} $scoreTeamAway",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = dimensionResource(id = R.dimen.live_score_line_height2).value.sp,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.live_score_font_size2).value.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    TeamField(
                        teamName = nameTeamAway,
                        teamLogo = logoTeamAway
                    )
                }
                Button(
                    onClick = {},
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.app_red_motive),
                        contentColor = colorResource(id = R.color.app_red_motive),
                        disabledContentColor = colorResource(id = R.color.app_red_motive),
                        disabledContainerColor = colorResource(id = R.color.app_red_motive)

                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.live_score_item_button_corners)))
                ) {
                    Text(
                        text = "Details",
                        color = Color.White,
                        lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun LeagueMatchesLiveShower(modifier: Modifier = Modifier) {
    Row(modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.live_score_item_live_shower_spacing), Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.big)))
                .background(color = colorResource(id = R.color.live_shower_background))
                .padding(
                    start = dimensionResource(id = R.dimen.live_score_item_live_shower_padding_start),
                    end = dimensionResource(id = R.dimen.live_score_item_live_shower_padding_end),
                    top = dimensionResource(id = R.dimen.live_score_item_live_shower_padding_top),
                    bottom = dimensionResource(id = R.dimen.live_score_item_live_shower_padding_bottom)
                )
        ) {
            Sizesm()
            Text(
                text = "89",
                color = colorResource(id = R.color.live_shower_dark_green),
                textAlign = TextAlign.Center,
                lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun Sizesm(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredSize(size = dimensionResource(id = R.dimen.live_score_item_live_shower_background))
    ) {
        Badge(
            containerColor = colorResource(id = R.color.live_shower_light_green),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = dimensionResource(id = R.dimen.live_score_item_live_shower_offset),
                    y = dimensionResource(id = R.dimen.live_score_item_live_shower_offset)
                )
        )
    }
}

@Composable
fun TeamField(
    modifier: Modifier = Modifier,
    teamName: String,
    teamLogo: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.live_score_item_team_field_spacing), Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredHeight(height = dimensionResource(id = R.dimen.live_score_item_team_field_height))
    ) {
        Image(
            painter = rememberAsyncImagePainter(teamLogo),
            contentDescription = "image 7",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .requiredSize(size = dimensionResource(id = R.dimen.live_score_item_team_field_image_size))
        )
        Text(
            text = teamName,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .requiredWidth(width = dimensionResource(id = R.dimen.live_score_item_team_field_text_width))
        )
    }
}
