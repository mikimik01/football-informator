package com.example.footballmanager.ui.theme.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.size.Size
import com.example.footballmanager.R
import com.example.footballmanager.ui.MasterViewModel

@Composable
fun ButtonNavigationBar(
    modifier: Modifier = Modifier, onNavigateToScreen: (ScreensEnum) -> Unit
) {

    val masterViewModel: MasterViewModel = hiltViewModel()

    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.bot_nav_bar_items_spacing), Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.highlighted_element_color))
            .padding(top = dimensionResource(id = R.dimen.bot_nav_bar_padding))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.bot_nav_bar_row_padding))
                .background(colorResource(R.color.highlighted_element_color))
        ) {
            val buttonColor = NavigationBarItemColors(
                selectedIconColor = colorResource(id = R.color.app_red_motive),
                selectedTextColor = Color.White,
                selectedIndicatorColor = colorResource(R.color.highlighted_element_color),
                unselectedIconColor = colorResource(id = R.color.unselected_navigation_item),
                unselectedTextColor = colorResource(id = R.color.unselected_navigation_item),
                disabledIconColor = colorResource(R.color.highlighted_element_color),
                disabledTextColor = colorResource(R.color.highlighted_element_color)
            )
            NavigationBar(
                modifier = Modifier.background(colorResource(R.color.highlighted_element_color)),
                containerColor = colorResource(R.color.highlighted_element_color),
            ) {
                ScreensEnum.entries.forEach { screen ->
                    NavigationBarItem(
                        selected = masterViewModel.currentBotNavSelection == screen,
                        onClick = { onNavigateToScreen(screen) },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = screen.iconRes),
                                modifier = modifier.size(dimensionResource(id = R.dimen.bot_nav_bar_item_size)),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = screen.name,
                                fontSize = dimensionResource(id = R.dimen.bot_nav_bar_font_size).value.sp
                            )
                        },
                        colors = buttonColor,
                        modifier = Modifier.background(colorResource(R.color.highlighted_element_color))
                    )
                }
            }

        }
    }
}