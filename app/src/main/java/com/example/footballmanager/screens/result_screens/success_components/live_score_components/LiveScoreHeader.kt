package com.example.footballmanager.screens.result_screens.success_components.live_score_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

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
            lineHeight = dimensionResource(id = R.dimen.live_score_text_live_now_line_height).value.sp,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.live_score_text_live_now_font_size).value.sp,
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

