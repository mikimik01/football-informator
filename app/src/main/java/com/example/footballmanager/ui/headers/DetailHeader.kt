package com.example.footballmanager.ui.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.footballmanager.R
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.providers.Providers
import kotlinx.coroutines.delay

private const val ANIMATION_DELAY = 500L
private const val ANIMATION_FRAMES = 4
private const val ANIMATION_STEP = 1
private const val INITIAL_INDEX = 0

@Composable
fun DetailHeader(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current
    val masterViewModel = Providers.localMasterModelProvider.current as MasterViewModel
    val navController = Providers.localNavControllerProvider.current as NavHostController
    val loading = stringResource(id = R.string.loading)
    val error = stringResource(id = R.string.header_error)
    val dot = stringResource(R.string.dot)
    var tittle = loading
    var day = loading
    var dotCount by remember { mutableIntStateOf(INITIAL_INDEX) }
    var currentUpperLetter by remember { mutableIntStateOf(INITIAL_INDEX) }
    when (val retrievedDataState = masterViewModel.retrievingMatchEventsState) {
        is RetrievingDataState.Success -> {
            tittle = retrievedDataState.matches.selectedItemElements.leagueName
            day = masterViewModel.formatDateString(
                retrievedDataState.matches.selectedItemElements.date,
                ctx
            )
        }

        is RetrievingDataState.Loading -> {
            LaunchedEffect(Unit) {
                while (true) {
                    delay(ANIMATION_DELAY)
                    dotCount = (dotCount + ANIMATION_STEP) % ANIMATION_FRAMES
                }
            }
            tittle = dot.repeat(dotCount) + loading + dot.repeat(dotCount)
            day = tittle
        }

        is RetrievingDataState.Error -> {
            LaunchedEffect(Unit) {
                while (true) {
                    delay(ANIMATION_DELAY)
                    currentUpperLetter = (currentUpperLetter + ANIMATION_STEP) % error.length
                }
            }
            tittle = buildString {
                error.forEachIndexed { index, char ->
                    append(if (index == currentUpperLetter) char.uppercase() else char)
                }
            }
            day = error
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = dimensionResource(id = R.dimen.detail_item_header_height))
    ) {
        Icon(
            tint = colorResource(id = R.color.app_white_motive),
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = stringResource(R.string.chevron_left),
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(start = dimensionResource(id = R.dimen.detail_item_header_icon_padding))
                .clickable {
                    masterViewModel.navigateUpToHome(navController)
                }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.detail_item_header_spacing),
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(
                    alignment = Alignment.Center
                )
        ) {
            Text(
                text = tittle,
                color = Color.White,
                lineHeight = dimensionResource(id = R.dimen.detail_item_header_line_height).value.sp,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.detail_item_header_font_size).value.sp
                )
            )
            Text(
                text = day,
                color = colorResource(id = R.color.app_darker_white_motive),
                lineHeight = dimensionResource(id = R.dimen.detail_item_header_inner_line_height).value.sp,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.detail_item_header_inner_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.detail_item_header_inner_spacing),
                Alignment.Start
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .padding(end = dimensionResource(id = R.dimen.detail_item_header_inner_padding))
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_share_24),
                contentDescription = stringResource(R.string.fluent_share_android_24_filled),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.image_tint)),
                modifier = Modifier
                    .requiredSize(size = dimensionResource(id = R.dimen.detail_item_header_image_size))
            )
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = stringResource(R.string.fluent_star_24_filled),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.image_tint)),
                modifier = Modifier
                    .requiredSize(size = dimensionResource(id = R.dimen.detail_item_header_image_size))
            )
        }
    }
}