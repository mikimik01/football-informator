package com.example.footballmanager.ui.screens.home.detail_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R
import com.example.footballmanager.data.entities.MatchEvent
import com.example.footballmanager.ui.screens.data_structures.ScorerNameAndTime
import com.example.footballmanager.ui.screens.home.home_components.success_components.live_score_components.LiveItemElements
import com.example.footballmanager.ui.screens.home.home_components.success_components.live_score_components.LiveItemUpperBody

@Composable
fun DetailItemView(
    events: List<MatchEvent>,
    modifier: Modifier = Modifier,
    selectedItemData: LiveItemElements
) {

    val listOfScorers = mutableListOf<ScorerNameAndTime>()
    events.forEach { event ->
        with(event) {
            if (type == stringResource(R.string.goal_type)) {
                listOfScorers.add(
                    ScorerNameAndTime(
                        name = player?.name ?: team?.name ?: stringResource(R.string.def_value),
                        elapsed = time?.elapsed ?: dimensionResource(id = R.dimen.detail_item_element_default_elapsed_time).value.toInt()
                    )
                )
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.detail_item_element_spacing), Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.detail_item_element_inner_spacing), Alignment.CenterVertically),
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.detail_item_element_corner)))
                .background(color = colorResource(id = R.color.item_row_color))
                .padding(all = dimensionResource(id = R.dimen.detail_item_element_padding))
        ) {
            LiveItemUpperBody(
                selectedItemData,
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(), color = colorResource(id = R.color.horizontal_divider)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.detail_item_element_item_spacing), Alignment.Top),
                    modifier = Modifier
                        .requiredWidth(width = dimensionResource(id = R.dimen.detail_item_element_item_width))
                ) {
                    Text(
                        text = "Goal Scorer",
                        color = colorResource(id = R.color.item_row_color),
                        textAlign = TextAlign.Center,
                        lineHeight = dimensionResource(id = R.dimen.detail_item_element_item_line_height).value.sp,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.detail_item_element_item_font_size).value.sp
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ball_icon),
                    contentDescription = stringResource(R.string.fluent_sport_soccer_24_filled),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.app_grey_motive)),
                    modifier = Modifier
                        .requiredSize(size = dimensionResource(id = R.dimen.detail_item_element_item_image_size))
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.detail_item_element_item_element_spacing), Alignment.Top),
                    horizontalAlignment = Alignment.End
                ) {
                    listOfScorers.forEach {
                        with(it) {
                            Text(
                                text = "$name $elapsed`",
                                color = colorResource(id = R.color.app_darker_white_motive),
                                textAlign = TextAlign.Center,
                                lineHeight = dimensionResource(id = R.dimen.detail_item_element_item_line_height).value.sp,
                                style = TextStyle(
                                    fontSize = dimensionResource(id = R.dimen.detail_item_element_item_font_size).value.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


