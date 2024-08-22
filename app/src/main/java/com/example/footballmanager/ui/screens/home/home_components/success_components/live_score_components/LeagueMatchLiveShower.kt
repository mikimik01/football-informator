package com.example.footballmanager.ui.screens.home.home_components.success_components.live_score_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

@Composable
fun LeagueMatchesLiveShower(modifier: Modifier = Modifier) {
    Row(modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.live_score_item_live_shower_spacing),
                Alignment.CenterHorizontally
            ),
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
            LeagueMatchesLiveShowerBackground()
            Text(
                text = "89", //Dont know what it is used for
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
fun LeagueMatchesLiveShowerBackground(modifier: Modifier = Modifier) {
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
