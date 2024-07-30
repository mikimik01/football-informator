package com.example.footballmanager.screens.result_screens.success_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 109.dp)
                .requiredHeight(height = 32.dp)
        ) {
            Text(
                text = "scorelive",
                color = Color.White,
                lineHeight = 1.33.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 99.dp,
                        y = 0.dp
                    )
                    .requiredSize(size = 10.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xfff63d68))
            )
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = 80.dp)
                .requiredHeight(height = 28.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.notiffication_icon),
                contentDescription = "fluent:alert-24-regular",
                colorFilter = ColorFilter.tint(Color(0xffeaecf0)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 52.dp,
                        y = 0.dp
                    )
                    .requiredSize(size = 28.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "fluent:search-24-regular",
                colorFilter = ColorFilter.tint(Color(0xffeaecf0)),
                modifier = Modifier
                    .requiredSize(size = 28.dp)
            )
        }
    }
}