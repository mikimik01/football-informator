package com.example.footballmanager.screens.result_screens.success_components

import android.content.res.Resources
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import coil.compose.rememberAsyncImagePainter
import com.example.footballmanager.R
import com.example.footballmanager.network.FixtureDataWrapper


@Composable
fun MatchScoreTab(
    fixture: FixtureDataWrapper,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .padding(horizontal = 16.dp)
    ) {
        val ctx = LocalContext.current
        LazyColumn {
            var previousLeagueName = ""
            for (item in fixture.responseBody) {
                val defaultValue = getString(ctx, R.string.default_value)
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
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(leagueLogo),
            contentDescription = "League logo",
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
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(5.dp)
            .requiredWidth(width = 358.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = Color(0xff1e1e1e))
            .padding(all = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
        ) {
            Text(
                text = short,
                color = Color(0xff5d5c64),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .requiredWidth(width = 36.dp)
            )
            Text(
                text = numberToAskDoNotKnowWhatItIs,
                color = Color(0xff5d5c64),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .requiredWidth(width = 36.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(61.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(weight = 1f)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(logoTeamHome),
                        contentDescription = "Home Logo",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                    )
                    Text(
                        text = nameTeamHome,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp,
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
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 40.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(120.dp, Alignment.Start),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(weight = 1f)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(logoTeamAway),
                        contentDescription = "Team away",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .requiredSize(size = 24.dp)
                    )
                    Text(
                        text = nameTeamAway,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp,
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
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 40.dp)
                )
            }
        }
    }
}

