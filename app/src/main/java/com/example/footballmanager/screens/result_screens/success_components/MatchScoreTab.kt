package com.example.footballmanager.screens.result_screens.success_components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.footballmanager.R
import com.example.footballmanager.network.FixtureDataWrapper


@Composable
fun MatchScoreTab(
    fixture: FixtureDataWrapper,
    modifier: Modifier = Modifier) {

    val fixturesCount = fixture.responseBody.size
    val league = fixture.responseBody[0].league?.name?:"null"
    val leagueLogo = fixture.responseBody[0].league?.logo?:"null"
    val team1 = fixture.responseBody[0].teams?.home?.name?:"null"
    val team2 = fixture.responseBody[0].teams?.away?.name?:"null"
    val short = fixture.responseBody[0].fixture?.status?.short?:"null"
    Log.d("Sprawdzaniestruktury", "League: $league")
    Log.d("Sprawdzaniestruktury", "Logo league: $leagueLogo")
    Log.d("Sprawdzaniestruktury", "Team1: $team1")
    Log.d("Sprawdzaniestruktury", "Team2 $team2")
    Log.d("Sprawdzaniestruktury", "Short: $short")


    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .padding(horizontal = 16.dp)
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
                    .clip(shape = RoundedCornerShape(12.dp)))
            Text(
                text = "Premier League",
                color = Color(0xffb6b6b6),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium))
        }

        LazyColumn {
            items(3){
                FixtureItem()
            }
        }

    }
}


@Composable
fun FixtureItem(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(10.dp)
            .requiredWidth(width = 358.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = Color(0xff1e1e1e))
            .padding(all = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)
        ) {
            Text(
                text = "FT ",
                color = Color(0xff5d5c64),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .requiredWidth(width = 36.dp))
            Text(
                text = "15/4",
                color = Color(0xff5d5c64),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .requiredWidth(width = 36.dp))
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
                        painter = painterResource(id = R.drawable.swipe_right_icon),
                        contentDescription = "image 7",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .requiredSize(size = 24.dp))
                    Text(
                        text = "West Ham United",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium))
                }
                Text(
                    text = "2",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .requiredWidth(width = 40.dp))
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
                        painter = painterResource(id = R.drawable.swipe_right_icon),
                        contentDescription = "image 7",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .requiredSize(size = 24.dp))
                    Text(
                        text = "Arsenal",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium))
                }
                Text(
                    text = "2",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .requiredWidth(width = 40.dp))
            }
        }
    }
}

