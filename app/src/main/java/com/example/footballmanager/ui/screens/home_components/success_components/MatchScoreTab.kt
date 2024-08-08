package com.example.footballmanager.ui.screens.home_components.success_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.footballmanager.R
import com.example.footballmanager.data.entities.Match
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

const val ITEMS_TO_LOAD = 10

@Composable
fun MatchScoreTab(
    matches: List<Match>, modifier: Modifier = Modifier,
    cached: Boolean
) {
    var lastLoadedIndex by remember {
        mutableIntStateOf(ITEMS_TO_LOAD)
    }
    val lazyListState = rememberLazyListState()
    val currentState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val shouldLoadMore =
        remember { derivedStateOf { currentState.value + ITEMS_TO_LOAD >= lastLoadedIndex } }
    val lastFixtureIndex = matches.size - 1

    LaunchedEffect(key1 = shouldLoadMore.value) {
        lastLoadedIndex += if (lastLoadedIndex + ITEMS_TO_LOAD < lastFixtureIndex) {
            ITEMS_TO_LOAD
        } else {
            (lastFixtureIndex - lastLoadedIndex)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.column_spacing), Alignment.Top
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.column_padding))
    ) {
        val defaultValue = stringResource(id = R.string.default_value)
        AnimatedVisibility(visible = cached) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    color = colorResource(id = R.color.app_darker_white_motive),
                    text = stringResource(R.string.data_retrieved_from_cache)
                )
            }
        }
        LazyColumn(
            state = lazyListState, modifier = Modifier.fillMaxWidth()
        ) {
            var previousLeagueName = defaultValue
            for (i in 0..lastLoadedIndex) {
                val item = matches[i]
                with(item) {
                    val leagueName = league?.name ?: defaultValue
                    val leagueLogo = league?.logo ?: defaultValue
                    val nameTeamHome = teams?.home?.name ?: defaultValue
                    val nameTeamAway = teams?.away?.name ?: defaultValue
                    val scoreTeamHome = goals?.home?.toString() ?: defaultValue
                    val scoreTeamAway = goals?.away?.toString() ?: defaultValue
                    val logoTeamHome = teams?.home?.logo ?: defaultValue
                    val logoTeamAway = teams?.away?.logo ?: defaultValue
                    val short = fixture?.status?.short ?: defaultValue
                    var date = fixture?.date ?: defaultValue

                    if (date != defaultValue) {
                        runCatching {
                            val offsetDateTime =
                                OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

                            val dateDate = offsetDateTime.toLocalDate()
                                .format(DateTimeFormatter.ofPattern("dd/MM"))
                            date = dateDate.toString()
                        }
                    }

                    if (leagueName != previousLeagueName) {
                        item {
                            LeagueHeader(
                                leagueLogo = leagueLogo, leagueName = leagueName
                            )
                        }
                    }
                    previousLeagueName = leagueName

                    item {
                        FixtureItem(
                            nameTeamHome = nameTeamHome,
                            nameTeamAway = nameTeamAway,
                            scoreTeamHome = scoreTeamHome,
                            scoreTeamAway = scoreTeamAway,
                            logoTeamHome = logoTeamHome,
                            logoTeamAway = logoTeamAway,
                            short = short,
                            fixtureDate = date
                        )

                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LeagueHeader(
    leagueLogo: String, leagueName: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.league_header_spacing), Alignment.Start
        ), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
    ) {
        GlideImage(
            model = leagueLogo,
            contentDescription = stringResource(R.string.league_logo),
            modifier = Modifier
                .requiredSize(size = dimensionResource(id = R.dimen.league_header_image_required_size))
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corners_radius))),
            contentScale = ContentScale.Crop
        )
        Text(
            text = leagueName,
            color = colorResource(id = R.color.app_darker_white_motive),
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.league_header_font_size).value.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FixtureItem(
    modifier: Modifier = Modifier,
    nameTeamHome: String,
    nameTeamAway: String,
    scoreTeamHome: String,
    scoreTeamAway: String,
    logoTeamHome: String,
    logoTeamAway: String,
    short: String,
    fixtureDate: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.item_row_spacing), Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.item_row_padding))
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.item_rounded_shape_radius)))
            .background(color = colorResource(id = R.color.item_row_color))
            .padding(all = dimensionResource(id = R.dimen.item_row_margin))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.item_column_spacing), Alignment.Top
            )
        ) {
            Text(
                text = short,
                color = colorResource(id = R.color.app_grey_motive),
                textAlign = TextAlign.Center,
                lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.requiredWidth(width = dimensionResource(id = R.dimen.short_and_date_required_width))
            )
            Text(
                text = fixtureDate,
                color = colorResource(id = R.color.app_grey_motive),
                textAlign = TextAlign.Center,
                lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.requiredWidth(width = dimensionResource(id = R.dimen.short_and_date_required_width))
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.league_names_column_spacing), Alignment.Top
            ), modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.league_names_row_spacing), Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.league_names_inner_row_spacing),
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(weight = 1f)
                ) {
                    GlideImage(
                        model = logoTeamHome,
                        contentDescription = stringResource(R.string.home_logo),
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.requiredSize(size = dimensionResource(id = R.dimen.team_icon_required_size))
                    )
                    Text(
                        text = nameTeamHome,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        maxLines = 1
                    )
                }
                Text(
                    text = scoreTeamHome,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.requiredWidth(width = dimensionResource(id = R.dimen.score_required_width))
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.league_names_row_spacing), Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.league_names_inner_row_spacing),
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(weight = 1f)
                ) {
                    GlideImage(
                        model = logoTeamAway,
                        contentDescription = stringResource(R.string.away_logo),
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.requiredSize(size = dimensionResource(id = R.dimen.team_icon_required_size))
                    )
                    Text(
                        text = nameTeamAway,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        maxLines = 1
                    )
                }
                Text(
                    text = scoreTeamAway,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = dimensionResource(id = R.dimen.text_height).value.sp,
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.requiredWidth(width = dimensionResource(id = R.dimen.score_required_width))
                )
            }

        }
    }
}

