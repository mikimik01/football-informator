package com.example.footballmanager.ui.screens.home.home_components.success_components.live_score_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.footballmanager.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamField(
    modifier: Modifier = Modifier,
    teamName: String,
    teamLogo: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.live_score_item_team_field_spacing),
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredHeight(height = dimensionResource(id = R.dimen.live_score_item_team_field_height))
    ) {
        GlideImage(
            model = teamLogo,
            contentDescription = stringResource(R.string.team_logo),
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .requiredSize(size = dimensionResource(id = R.dimen.live_score_item_team_field_image_size))
        )
        Text(
            text = teamName,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .requiredWidth(width = dimensionResource(id = R.dimen.live_score_item_team_field_text_width))
        )
    }
}
