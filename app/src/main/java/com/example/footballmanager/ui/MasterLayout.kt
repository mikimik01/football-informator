package com.example.footballmanager.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballmanager.ui.bottom_navigation.AdditionalScreens
import com.example.footballmanager.ui.screens.main.AccountScreen
import com.example.footballmanager.ui.screens.main.CompetitionScreen
import com.example.footballmanager.ui.screens.main.HomeScreen
import com.example.footballmanager.ui.screens.main.NewsScreen
import com.example.footballmanager.ui.headers.HomeHeader
import com.example.footballmanager.ui.bottom_navigation.BottomNavigationBar
import com.example.footballmanager.ui.bottom_navigation.MainScreens
import com.example.footballmanager.ui.headers.DetailHeader
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.main.DetailScreen
import com.example.footballmanager.ui.theme.topAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballManagerApp(
    masterViewModel: MasterViewModel = hiltViewModel(),
    userName: String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val ctx = LocalContext.current
    val navController = rememberNavController()

    val navigationBarSwitcher by masterViewModel.currentSelectedHeader

    with(Providers) {
        CompositionLocalProvider(
            localViewModelProvider provides masterViewModel,
            localNavControllerProvider provides navController
        ) {

            LaunchedEffect(key1 = true) {
                masterViewModel.getFixturesData(ctx = ctx)
                masterViewModel.getFixturesLiveNow()
            }

            Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(colors = topAppBarColors, scrollBehavior = scrollBehavior, title = {
                        AnimatedVisibility(visible = navigationBarSwitcher == HeaderType.HomeHeader) {
                            HomeHeader(currentUserName = userName)
                        }
                        AnimatedVisibility(visible = navigationBarSwitcher == HeaderType.DetailHeader) {
                            DetailHeader()
                        }
                    })
                },
                bottomBar = {
                    BottomNavigationBar(onNavigateToScreen = { screen ->
                        navController.navigate(screen.name)
                        masterViewModel.currentBotNavSelection = screen
                    })
                }) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    NavigationManager(
                        navController = navController,
                        masterViewModel = masterViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationManager(navController: NavHostController, masterViewModel: MasterViewModel) {
    NavHost(
        navController = navController, startDestination = MainScreens.Home.name
    ) {
        composable(route = MainScreens.Home.name) {
            HomeScreen(
                retrievingByDateState = masterViewModel.retrievingByDateState,
                retrievedByLiveNowState = masterViewModel.retrievingByLiveNowState
            )
        }
        composable(route = MainScreens.Competition.name) {
            CompetitionScreen()
        }
        composable(route = MainScreens.News.name) {
            NewsScreen()
        }
        composable(route = MainScreens.Account.name) {
            AccountScreen()
        }
        composable(route = AdditionalScreens.Detail.name) {
            DetailScreen()
        }
    }
}

