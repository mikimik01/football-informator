package com.example.footballmanager.screens.result_screens.success_components

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R
import com.example.footballmanager.network.structures.FixtureDataWrapper

@Composable
fun LiveScoreTab(
    modifier: Modifier = Modifier,
    liveNowFixtures: FixtureDataWrapper
    ) {
    //if (liveNowFixtures.responseBody.size != 0) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp
                )
        ) {
            LiveScoreHeader()
            LazyRow {
                items(4) {
                    LiveScoreItem()
                }
            }

        }
    //}
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
            text = "Live Now",
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 1.56.em,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "See More",
            color = Color(0xfff63d68),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveScoreItem(){
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
                            painter = painterResource(id = R.drawable.notiffication_icon),
                            contentDescription = "image 6",
                            modifier = Modifier
                                .requiredSize(size = 20.dp)
                                .clip(shape = RoundedCornerShape(12.dp))
                        )
                        Text(
                            text = "Premier League",
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
                    TeamField()
                    Text(
                        text = "0 - 2",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    TeamField()
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
fun TeamField(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredHeight(height = 76.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.notiffication_icon),
            contentDescription = "image 7",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .requiredSize(size = 32.dp)
        )
        Text(
            text = "Udinese",
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
    LiveScoreTab(liveNowFixtures = FixtureDataWrapper())
}