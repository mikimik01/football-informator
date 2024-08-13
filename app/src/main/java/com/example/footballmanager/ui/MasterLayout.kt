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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballmanager.ui.screens.main.AccountScreen
import com.example.footballmanager.ui.screens.main.CompetitionScreen
import com.example.footballmanager.ui.screens.main.HomeScreen
import com.example.footballmanager.ui.screens.main.NewsScreen
import com.example.footballmanager.ui.theme.Header
import com.example.footballmanager.ui.theme.background
import com.example.footballmanager.ui.theme.navigation.ButtonNavigationBar
import com.example.footballmanager.ui.theme.navigation.ScreensEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballManagerApp(userName: String) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val masterViewModel: MasterViewModel = hiltViewModel()
    val ctx = LocalContext.current

    val navController = rememberNavController()

    LaunchedEffect(key1 = true) {
        masterViewModel.getFixturesData(ctx = ctx)
        masterViewModel.getFixturesLiveNow()
    }

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppTopBar(scrollBehavior = scrollBehavior, currentUserName = userName) },
        bottomBar = {
            ButtonNavigationBar(onNavigateToScreen = { screen ->
                navController.navigate(screen.name)
                masterViewModel.currentBotNavSelection = screen
            })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavigationManager(navController = navController, masterViewModel = masterViewModel)
        }
    }
}

@Composable
fun NavigationManager(navController: NavHostController, masterViewModel: MasterViewModel) {
    NavHost(
        navController = navController, startDestination = ScreensEnum.Home.name
    ) {
        composable(route = ScreensEnum.Home.name) {
            HomeScreen(
                retrievingByDateState = masterViewModel.retrievingByDateState,
                retrievedByLiveNowState = masterViewModel.retrievingByLiveNowState
            )
        }
        composable(route = ScreensEnum.Competition.name) {
            CompetitionScreen()
        }
        composable(route = ScreensEnum.News.name) {
            NewsScreen()
        }
        composable(route = ScreensEnum.Account.name) {
            AccountScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    currentUserName: String
) {
    TopAppBar(colors = TopAppBarColors(
        containerColor = background,
        titleContentColor = background,
        actionIconContentColor = background,
        navigationIconContentColor = background,
        scrolledContainerColor = background
    ), scrollBehavior = scrollBehavior, title = {
        Header(currentUserName = currentUserName)
    })
}