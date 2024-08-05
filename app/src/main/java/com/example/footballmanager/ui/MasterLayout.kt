package com.example.footballmanager.ui

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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.ui.screens.AccountScreen
import com.example.footballmanager.ui.screens.CompetitionScreen
import com.example.footballmanager.ui.screens.HomeScreen
import com.example.footballmanager.ui.screens.NewsScreen
import com.example.footballmanager.ui.theme.ButtonNavigationBar
import com.example.footballmanager.ui.theme.Header
import com.example.footballmanager.ui.theme.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballManagerApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val masterViewModel: MasterViewModel = hiltViewModel()
    val ctx = LocalContext.current
    LaunchedEffect(key1 = true) {
        masterViewModel.getFixturesData(ctx = ctx)
        masterViewModel.getFixturesLiveNow()
    }
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppTopBar(scrollBehavior = scrollBehavior) },
        bottomBar = {
            ButtonNavigationBar()
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when(masterViewModel.currentScreen){
                is Screens.Home -> HomeScreen(masterViewModel.retrievingByDateState)
                is Screens.Account -> AccountScreen()
                Screens.Competition -> CompetitionScreen()
                Screens.News -> NewsScreen()
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier
) {
    TopAppBar(colors = TopAppBarColors(
        containerColor = background,
        titleContentColor = background,
        actionIconContentColor = background,
        navigationIconContentColor = background,
        scrolledContainerColor = background
    ), scrollBehavior = scrollBehavior, title = {
        Header()
    })
}
