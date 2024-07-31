package com.example.footballmanager.screens.result_screens.success_components

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.footballmanager.R
import com.example.footballmanager.network.FixtureDataWrapper
import com.example.footballmanager.screens.view_models.HomeViewModel
import java.time.LocalDate
import kotlin.math.log


@Composable
fun MatchScoreTab(
    fixture: FixtureDataWrapper,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    var lastLoadedAmount by remember {
        mutableIntStateOf(10)
    }
    val lazyListState = rememberLazyListState()
    val currentState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    val shouldLoadMore = remember { derivedStateOf { currentState.value + 10 >= lastLoadedAmount } }

    LaunchedEffect(key1 = shouldLoadMore.value) {
        lastLoadedAmount += 10
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.column_spacing),
            Alignment.Top
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.column_padding))
    ) {
        val defaultValue = stringResource(id = R.string.default_value)
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var previousLeagueName = defaultValue
            for (i in 0..lastLoadedAmount) {
                val item = fixture.responseBody[i]
                val leagueName = item.league?.name ?: defaultValue
                val leagueLogo = item.league?.logo ?: defaultValue
                val nameTeamHome = item.teams?.home?.name ?: defaultValue
                val nameTeamAway = item.teams?.away?.name ?: defaultValue
                val scoreTeamHome = item.goals?.home?.toString() ?: defaultValue
                val scoreTeamAway = item.goals?.away?.toString() ?: defaultValue
                val logoTeamHome = item.teams?.home?.logo ?: defaultValue
                val logoTeamAway = item.teams?.away?.logo ?: defaultValue
                val short = item.fixture?.status?.short ?: defaultValue

                if (leagueName != previousLeagueName) {
                    item {
                        LeagueHeader(
                            leagueLogo = leagueLogo,
                            leagueName = leagueName
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
                        numberToAskDoNotKnowWhatItIs = "15/4"
                    )

                }
            }
        }

    }
}

@Composable
fun LeagueHeader(
    leagueLogo: String,
    leagueName: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.league_header_spacing),
            Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(leagueLogo),
            contentDescription = stringResource(R.string.league_logo),
            modifier = Modifier
                .requiredSize(size = dimensionResource(id = R.dimen.league_header_image_required_size))
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corners_radius)))
        )
        Text(
            text = leagueName,
            color = colorResource(id = R.color.league_header_text_color),
            textAlign = TextAlign.Center,
            lineHeight = 1.5.em, //nie wiem jak to przeniesc do res
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.league_header_font_size).value.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

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
    numberToAskDoNotKnowWhatItIs: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.item_row_spacing),
            Alignment.Start
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
                dimensionResource(id = R.dimen.item_column_spacing),
                Alignment.Top
            )
        ) {
            Text(
                text = short,
                color = colorResource(id = R.color.date_snd_short_color),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em, // nie wiem jak to wyeksportować
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .requiredWidth(width = dimensionResource(id = R.dimen.short_and_date_required_width))
            )
            Text(
                text = numberToAskDoNotKnowWhatItIs,
                color = colorResource(id = R.color.date_snd_short_color),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .requiredWidth(width = dimensionResource(id = R.dimen.short_and_date_required_width))
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.league_names_column_spacing),
                Alignment.Top
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.league_names_row_spacing),
                    Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.league_names_inner_row_spacing),
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(weight = 1f)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(logoTeamHome),
                        contentDescription = "Home Logo",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .requiredSize(size = dimensionResource(id = R.dimen.team_icon_required_size))
                    )
                    Text(
                        text = nameTeamHome,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Text(
                    text = scoreTeamHome,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em,
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .requiredWidth(width = dimensionResource(id = R.dimen.score_required_width))
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.away_row_spacing),
                    Alignment.Start
                ),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.league_names_inner_row_spacing),
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(weight = 1f)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(logoTeamAway),
                        contentDescription = "Team away",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .requiredSize(size = dimensionResource(id = R.dimen.team_icon_required_size))
                    )
                    Text(
                        text = nameTeamAway,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Text(
                    text = scoreTeamAway,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em,
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.items_font_size).value.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .requiredWidth(width = dimensionResource(id = R.dimen.score_required_width))
                )
            }
        }
    }
}

