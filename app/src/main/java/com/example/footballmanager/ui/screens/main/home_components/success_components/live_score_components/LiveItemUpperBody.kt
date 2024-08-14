package com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.footballmanager.R
import com.example.footballmanager.data.entities.League

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LiveItemUpperBody(
    liveItemElements: LiveItemElements,
) {
    with(liveItemElements) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.live_score_item_spacing), Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GlideImage(
                    model = leagueLogo,
                    contentDescription = stringResource(id = R.string.league_logo),
                    modifier = Modifier
                        .requiredSize(size = dimensionResource(id = R.dimen.live_score_item_image_size))
                        .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.medium)))
                )
                Text(
                    text = leagueName,
                    color = colorResource(id = R.color.app_darker_white_motive),
                    textAlign = TextAlign.Center,
                    lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            LeagueMatchesLiveShower()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
            ,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TeamField(
                teamName = nameTeamHome, teamLogo = logoTeamHome
            )
            Text(
                text = "$scoreTeamHome ${stringResource(id = R.string.score_separator)} $scoreTeamAway",
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = dimensionResource(id = R.dimen.live_score_line_height2).value.sp,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.live_score_font_size2).value.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TeamField(
                teamName = nameTeamAway, teamLogo = logoTeamAway
            )
        }
    }
}