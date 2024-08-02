package com.example.footballmanager.screens.result_screens.success_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.R
import com.example.footballmanager.screens.view_models.HomeViewModel
import com.example.footballmanager.screens.view_models.RetrievedData
import kotlinx.coroutines.launch

const val COUNT_OF_ITEMS_TO_START_PAGINATION = 6
const val CORRECT_TO_GET_TODAY_DATE_CENTERED = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabDate(modifier: Modifier = Modifier) {

    val homeViewModel: HomeViewModel = viewModel()
    val gotDates = homeViewModel.get7DatesToDisplay()
    var listOfDates = remember {
        mutableStateListOf<RetrievedData>().apply {
            addAll(gotDates)
        }
    }

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        lazyListState.scrollToItem(listOfDates.size / 2 - CORRECT_TO_GET_TODAY_DATE_CENTERED)
    }

    val currentState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    LaunchedEffect(key1 = currentState.value) {
        if (currentState.value + COUNT_OF_ITEMS_TO_START_PAGINATION > listOfDates.size) {
            val oneMoreDay = homeViewModel.getOneMoreDay(currentState.value + 1)
            listOfDates = listOfDates.apply {
                add(oneMoreDay)
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.row_spacing), Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.row_padding))
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.inner_row_spacing), Alignment.Start
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.inner_row_rounded_radius)))
                    .padding(all = dimensionResource(id = R.dimen.inner_row_padding))
            ) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        val scrollTo = currentState.value - 1
                        if (scrollTo >= 0) {
                            lazyListState.animateScrollToItem(currentState.value - 1)
                        }
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.swipe_left_icon),
                        contentDescription = "chevron-right",
                        modifier = Modifier.requiredSize(size = dimensionResource(id = R.dimen.icons_size))
                    )
                }
            }
        }
        Column(

            Modifier
                .weight(1f)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow(
                state = lazyListState,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
            ) {
                items(listOfDates.size) { index ->
                    DateField(
                        dayOfWeek = listOfDates[index].dayOfWeek,
                        dayAndMonth = listOfDates[index].restOfDate,
                        highlighted = index == currentState.value + CORRECT_TO_GET_TODAY_DATE_CENTERED
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.inner_row_spacing), Alignment.Start
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.inner_row_rounded_shape)))
                    .padding(all = dimensionResource(id = R.dimen.inner_row_rounded_radius))
            ) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(currentState.value + 1)
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.swipe_right_icon),
                        contentDescription = "chevron-right",
                        modifier = Modifier.requiredSize(size = dimensionResource(id = R.dimen.icons_size))
                    )
                }
            }
        }
    }
}

@Composable
fun DateField(
    modifier: Modifier = Modifier,
    highlighted: Boolean = false,
    dayOfWeek: String,
    dayAndMonth: String
) {
    Row(
        modifier = modifier.requiredWidth(width = dimensionResource(id = R.dimen.single_date_required_width))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.single_date_rounded_shape)))
                .padding(
                    horizontal = dimensionResource(id = R.dimen.single_date_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.single_date_padding_vertical)
                )
        ) {
            Text(
                text = dayOfWeek,
                color = if (highlighted) Color.White else colorResource(id = R.color.app_grey_motive),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = dimensionResource(id = R.dimen.single_date_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = dayAndMonth,
                color = if (highlighted) Color.White else colorResource(id = R.color.app_grey_motive),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = dimensionResource(id = R.dimen.single_date_font_size).value.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}
