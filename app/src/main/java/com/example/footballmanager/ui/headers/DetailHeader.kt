package com.example.footballmanager.ui.headers

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R


@Composable
fun DetailHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .requiredHeight(height = 52.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.notiffication_icon),
            contentDescription = "chevron-left",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 16.dp,
                    y = 14.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 143.dp,
                    y = 0.dp)
        ) {
            Text(
                text = "Premier League",
                color = Color.White,
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 14.sp)
            )
            Text(
                text = "Today",
                color = Color(0xffb6b6b6),
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 318.dp,
                    y = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "fluent:share-android-24-filled",
                colorFilter = ColorFilter.tint(Color(0xffdadada)),
                modifier = Modifier
                    .requiredSize(size = 20.dp))
            Image(
                painter = painterResource(id = R.drawable.notiffication_icon),
                contentDescription = "fluent:star-24-filled",
                colorFilter = ColorFilter.tint(Color(0xffdadada)),
                modifier = Modifier
                    .requiredSize(size = 20.dp))
        }
    }
}

