package com.example.footballmanager.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.headers.HeaderType
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.main.detail_components.DetailItemView
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveScoreItem

@Composable
fun DetailScreen(){
    val masterViewModel = Providers.localViewModelProvider.current as MasterViewModel
    val navController = Providers.localNavControllerProvider.current as NavHostController
//    Text(text = "Dtail Screen")
//    TextButton(onClick = {
//        masterViewModel.changeHeader(HeaderType.HomeHeader)
//        navController.navigateUp()
//    }) {
//        Text(
//            fontSize = 30.sp,
//            text = "Back")
//    }
    DetailItemView(
        selectedItemData = masterViewModel.selectedDetailItemData.value,
        modifier = Modifier.fillMaxWidth()
        )

}