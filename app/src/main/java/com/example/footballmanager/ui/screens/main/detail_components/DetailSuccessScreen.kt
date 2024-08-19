package com.example.footballmanager.ui.screens.main.detail_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.footballmanager.data.entities.MatchEvent
import com.example.footballmanager.ui.DetailScreenData
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.providers.Providers

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