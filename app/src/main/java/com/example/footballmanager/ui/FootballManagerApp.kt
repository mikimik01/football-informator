package com.example.footballmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.screens.HomeScreen
import com.example.footballmanager.screens.result_screens.success_components.Header
import com.example.footballmanager.screens.result_screens.success_components.LiveScoreTab
import com.example.footballmanager.screens.view_models.HomeViewModel
import com.example.footballmanager.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballManagerApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeViewModel: HomeViewModel = viewModel()

    LaunchedEffect(key1 = true) {
        homeViewModel.getFixturesData()
    }


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppTopBar(scrollBehavior = scrollBehavior) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            HomeScreen(homeViewModel.retrievingDataState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = background,
            titleContentColor = background,
            actionIconContentColor = background,
            navigationIconContentColor = background,
            scrolledContainerColor = background
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Header()
        }
    )
}

@Preview()
@Composable
private fun Frame16Preview() {
    FootballManagerApp()
}

