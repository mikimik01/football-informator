package com.example.footballmanager.ui.screens.home.detail_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.ui.screens.data_structures.DetailScreenData

@Composable
fun DetailSuccessScreen(
    detailScreenData: DetailScreenData
){
    DetailItemView(
        events = detailScreenData.matchEvents,
        selectedItemData = detailScreenData.selectedItemElements,
        modifier = Modifier.fillMaxWidth()
    )
}