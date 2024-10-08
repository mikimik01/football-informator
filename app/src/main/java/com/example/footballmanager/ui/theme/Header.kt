package com.example.footballmanager.ui.theme

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

@Composable
fun Header(
    modifier: Modifier = Modifier, currentUserName: String
) {
    Column {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.header_padding),
                    vertical = dimensionResource(id = R.dimen.header_padding_vertical)
                )

        ) {

            Box(
                modifier = Modifier.wrapContentWidth()
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.header_tittle),
                        color = Color.White,
                        lineHeight = dimensionResource(id = R.dimen.text_height_head).value.sp,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = dimensionResource(id = R.dimen.header_font_size).value.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "($currentUserName)",
                        color = colorResource(id = R.color.app_darker_white_motive),
                        lineHeight = dimensionResource(id = R.dimen.text_height_head_name).value.sp,
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = dimensionResource(id = R.dimen.header_font_size_name).value.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = dimensionResource(id = R.dimen.header_dot_offset_x),
                            y = dimensionResource(id = R.dimen.header_dot_offset_y)
                        )
                        .requiredSize(size = dimensionResource(id = R.dimen.header_dot_required_size))
                        .clip(shape = CircleShape)
                        .background(color = colorResource(id = R.color.app_red_motive))
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
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.app_white_motive)),
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
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.app_white_motive)),
                    modifier = Modifier.requiredSize(size = dimensionResource(id = R.dimen.header_icons_required_size))
                )
            }

        }
    }

}