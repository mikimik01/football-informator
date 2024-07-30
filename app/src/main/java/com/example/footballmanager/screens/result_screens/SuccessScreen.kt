package com.example.footballmanager.screens.result_screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.footballmanager.network.FixtureDataWrapper
import com.example.footballmanager.screens.result_screens.success_components.MatchScoreTab
import com.example.footballmanager.screens.result_screens.success_components.TabDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SuccessScreen(fixture: FixtureDataWrapper){
    TabDate()
    MatchScoreTab(fixture)
}