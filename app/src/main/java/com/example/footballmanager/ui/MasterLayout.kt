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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballmanager.R
import com.example.footballmanager.ui.bottom_navigation.AdditionalScreens
import com.example.footballmanager.ui.screens.home.AccountScreen
import com.example.footballmanager.ui.screens.home.CompetitionScreen
import com.example.footballmanager.ui.screens.home.HomeScreen
import com.example.footballmanager.ui.screens.home.NewsScreen
import com.example.footballmanager.ui.headers.HomeHeader
import com.example.footballmanager.ui.bottom_navigation.BottomNavigationBar
import com.example.footballmanager.ui.bottom_navigation.MainScreens
import com.example.footballmanager.ui.headers.DetailHeader
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.home.DetailScreen
import com.example.footballmanager.ui.theme.topAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballManagerApp(
    masterViewModel: MasterViewModel = hiltViewModel(),
    userName: String
) {
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val enterScrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val ctx = LocalContext.current
    val navController = rememberNavController()
    val navigationBarSwitcher by masterViewModel.currentSelectedHeader

    var scrollBehavior by remember {
        mutableStateOf(enterScrollBehaviour)
    }
    LaunchedEffect(key1 = masterViewModel.currentSelectedHeader.value.isScrollEnable) {
        scrollBehavior.state.heightOffset = 0f
        scrollBehavior.state.contentOffset = 0f
        scrollBehavior = if (masterViewModel.currentSelectedHeader.value.isScrollEnable) enterScrollBehaviour else pinnedScrollBehavior
    }

    with(Providers) {
        CompositionLocalProvider(
            localMasterModelProvider provides masterViewModel,
            localNavControllerProvider provides navController
        ) {

            LaunchedEffect(key1 = true) {
                masterViewModel.getFixturesData(ctx = ctx)
                masterViewModel.getFixturesLiveNow()
            }

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.header_padding_top)),
                        colors = topAppBarColors,
                        scrollBehavior = scrollBehavior,
                        title = {
                            AnimatedVisibility(visible = navigationBarSwitcher == HeaderType.MainHeader) {
                                HomeHeader(currentUserName = userName)
                            }
                            AnimatedVisibility(visible = navigationBarSwitcher == HeaderType.DetailHeader) {
                                DetailHeader()
                            }
                        }
                    )
                },
                bottomBar = {
                    BottomNavigationBar(onNavigateToScreen = { screen ->
                        masterViewModel.navigateToOneOfHomeScreens(
                            navController = navController,
                            screen = screen
                        )
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
    val ctx = LocalContext.current
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
        composable(
            route = AdditionalScreens.Detail.name
        ) {
                DetailScreen()

        }
    }
}

