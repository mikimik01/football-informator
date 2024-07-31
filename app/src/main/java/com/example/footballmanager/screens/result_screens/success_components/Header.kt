package com.example.footballmanager.screens.result_screens.success_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.header_padding))
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Text(
                text = stringResource(id = R.string.header_tittle),
                color = Color.White,
                lineHeight = 1.33.em, //nie wiem jak to wyeksportowac
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = dimensionResource(id = R.dimen.header_font_size).value.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = dimensionResource(id = R.dimen.header_dot_offset_x),
                        y = dimensionResource(id = R.dimen.header_dot_offset_y)
                    )
                    .requiredSize(size = dimensionResource(id = R.dimen.header_dot_required_size))
                    .clip(shape = CircleShape)
                    .background(color = colorResource(id = R.color.headerDotColor))
            )
        }
        Box(
            modifier = Modifier
                .requiredWidth(width = dimensionResource(id = R.dimen.header_icon_box_required_width))
                .requiredHeight(height = dimensionResource(id = R.dimen.header_icon_box_required_height))
        ) {
            Image(
                painter = painterResource(id = R.drawable.notiffication_icon),
                contentDescription = stringResource(id = R.string.notification_icon_content_description),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.notification_icon_tint_color)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = dimensionResource(id = R.dimen.header_notification_icon_offset_x),
                        y = dimensionResource(id = R.dimen.header_notification_icon_offset_y)
                    )
                    .requiredSize(size = dimensionResource(id = R.dimen.header_icons_required_size))
            )
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = stringResource(id = R.string.search_icon_content_description),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.search_icon_tint_color)),
                modifier = Modifier
                    .requiredSize(size = dimensionResource(id = R.dimen.header_icons_required_size))
            )
        }
    }
}