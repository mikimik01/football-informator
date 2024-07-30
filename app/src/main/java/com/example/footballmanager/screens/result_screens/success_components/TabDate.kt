package com.example.footballmanager.screens.result_screens.success_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.R
import com.example.footballmanager.screens.view_models.HomeViewModel
import com.example.footballmanager.screens.view_models.RetrievedData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabDate(modifier: Modifier = Modifier) {

    val ctx = LocalContext.current
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
        lazyListState.scrollToItem(listOfDates.size / 2 - 1)
    }

    val currentState = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    LaunchedEffect(key1 = currentState.value) {
        if (currentState.value + 6 > listOfDates.size) {
            val oneMoreDay = homeViewModel.getOneMoreDay(currentState.value + 1)
            listOfDates = listOfDates.apply {
                add(oneMoreDay)
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .padding(vertical = 12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .padding(all = 4.dp)
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            val scrollTo = currentState.value - 1
                            if (scrollTo >= 0) {
                                lazyListState.animateScrollToItem(currentState.value - 1)
                            }
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.swipe_left_icon),
                        contentDescription = "chevron-right",
                        modifier = Modifier.requiredSize(size = 20.dp)
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
                        highlighted = index == currentState.value + 2
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .padding(all = 4.dp)
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(currentState.value + 1)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.swipe_right_icon),
                        contentDescription = "chevron-right",
                        modifier = Modifier.requiredSize(size = 20.dp)
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
        modifier = modifier.requiredWidth(width = 60.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .padding(
                    horizontal = 8.dp, vertical = 4.dp
                )
        ) {
            Text(
                text = dayOfWeek,
                color = if (highlighted) Color.White else Color(0xff5d5c64),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 12.sp, fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = dayAndMonth,
                color = if (highlighted) Color.White else Color(0xff5d5c64),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 12.sp, fontWeight = FontWeight.Medium
                )
            )
        }
    }
}
