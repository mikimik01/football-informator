package com.example.footballmanager.screens.result_screens.success_components

import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.footballmanager.R
import com.example.footballmanager.network.structures.FixtureDataWrapper
import com.example.footballmanager.screens.view_models.HomeViewModel
import com.example.footballmanager.screens.view_models.RetrievingDataState

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
        mutableIntStateOf(liveNowFixturesCount-1)
    }
    val isNotEmpty by remember {
        mutableStateOf((liveNowFixturesCount != 0))
    }

    AnimatedVisibility(visible = isNotEmpty) {
        if (isNotEmpty) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp
                    )
            ) {
                LiveScoreHeader()
                val defaultValue = stringResource(id = R.string.default_value)
                LazyRow {
                    for (i in 0..lastLiveNowFixtureIndex) {
                        val item = liveNowFixtures.responseBody[i]
                        with(item){
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
            .padding(horizontal = 16.dp)
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
            color = Color(0xfff63d68),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp,
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
    Box(modifier = Modifier.padding(start = 16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            modifier = Modifier
                .requiredWidth(width = 340.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = colorResource(id = R.color.highlighted_element_color))
                    .padding(all = 12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(leagueLogo),
                            contentDescription = "image 6",
                            modifier = Modifier
                                .requiredSize(size = 20.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                        )
                        Text(
                            text = leagueName,
                            color = Color(0xffb6b6b6),
                            textAlign = TextAlign.Center,
                            lineHeight = 1.5.em,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    LeagueMatchesLiveShower()
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
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
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 20.sp,
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
                        containerColor = Color(0xfff63d68),
                        contentColor = Color(0xfff63d68),
                        disabledContentColor = Color(0xfff63d68),
                        disabledContainerColor = Color(0xfff63d68)

                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = "Details",
                        color = Color.White,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp
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
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color(0xffecfdf3))
                .padding(
                    start = 6.dp,
                    end = 8.dp,
                    top = 2.dp,
                    bottom = 2.dp
                )
        ) {
            Sizesm()
            Text(
                text = "89",
                color = Color(0xff027a48),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
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
            .requiredSize(size = 8.dp)
    ) {
        Badge(
            containerColor = Color(0xff12b76a),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 1.dp,
                    y = 1.dp
                )
        )
    }
}

@Composable
fun TeamField(
    modifier: Modifier = Modifier,
    teamName:String,
    teamLogo:String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredHeight(height = 76.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(teamLogo),
            contentDescription = "image 7",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .requiredSize(size = 32.dp)
        )
        Text(
            text = teamName,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 1.5.em,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .requiredWidth(width = 104.dp)
        )
    }
}


@Preview(widthDp = 390, heightDp = 240)
@Composable
private fun Frame16Preview() {
    LeagueMatchesLiveShower()
}