package com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.footballmanager.R
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.bottom_navigation.AdditionalScreens
import com.example.footballmanager.ui.bottom_navigation.MainScreens
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.providers.Providers

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LiveScoreItem(
    liveItemElements: LiveItemElements
) {
    val masterViewModel = Providers.localViewModelProvider.current as MasterViewModel
    val navController = Providers.localNavControllerProvider.current as NavHostController
    val boxPaddingStart = dimensionResource(id = R.dimen.big)
    Box(
        modifier = Modifier.padding(start = boxPaddingStart)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.medium), Alignment.Start
            ),
            modifier = Modifier.requiredWidth(width = dimensionResource(id = R.dimen.live_score_item_width))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.medium), Alignment.CenterVertically
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.live_score_item_corners)))
                    .background(color = colorResource(id = R.color.highlighted_element_color))
                    .padding(all = dimensionResource(id = R.dimen.medium))
            ) {
                LiveItemUpperBody(liveItemElements = liveItemElements)
                Button(
                    onClick = {
                        masterViewModel.changeDetailViewData(liveItemElements)
                        masterViewModel.changeHeader(HeaderType.DetailHeader)
                        navController.navigate(route = AdditionalScreens.Detail.name)
                    },
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.app_red_motive),
                        contentColor = colorResource(id = R.color.app_red_motive),
                        disabledContentColor = colorResource(id = R.color.app_red_motive),
                        disabledContainerColor = colorResource(id = R.color.app_red_motive)

                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.live_score_item_button_corners)))
                ) {
                    Text(
                        text = stringResource(R.string.details),
                        color = Color.White,
                        lineHeight = dimensionResource(id = R.dimen.live_score_line_height).value.sp,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.live_score_font_size).value.sp
                        )
                    )
                }
            }
        }
    }
}