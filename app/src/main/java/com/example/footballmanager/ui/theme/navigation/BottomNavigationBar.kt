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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballmanager.R
import com.example.footballmanager.ui.MasterViewModel

@Composable
fun ButtonNavigationBar(
    modifier: Modifier = Modifier,
    onNavigateToScreen: (Screens) -> Unit
) {

    val masterViewModel: MasterViewModel = hiltViewModel()

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.highlighted_element_color))
            .padding(top = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(colorResource(R.color.highlighted_element_color))
        ) {
            val buttonColor = NavigationBarItemColors(
                selectedIconColor = Color(0xfff63d68),
                selectedTextColor = Color.White,
                selectedIndicatorColor = colorResource(R.color.highlighted_element_color),
                unselectedIconColor = Color(0xff667085),
                unselectedTextColor = Color(0xff667085),
                disabledIconColor = colorResource(R.color.highlighted_element_color),
                disabledTextColor = colorResource(R.color.highlighted_element_color)
            )
            NavigationBar(
                modifier = Modifier
                    .background(colorResource(R.color.highlighted_element_color)),
                containerColor = colorResource(R.color.highlighted_element_color),
            ) {
                Screens.entries.forEach { screen ->
                    NavigationBarItem(
                        selected = masterViewModel.currentBotNavSelection == screen,
                        onClick = { onNavigateToScreen(screen) },
                        icon = { NavItemIcon(imgVec = ImageVector.vectorResource(id = screen.iconRes)) },
                        label = { NavItemText(screen.name) },
                        colors = buttonColor,
                        modifier = Modifier
                            .background(colorResource(R.color.highlighted_element_color))
                    )
                }
            }

        }
    }
}

@Composable
fun NavItemIcon(modifier: Modifier = Modifier, imgVec: ImageVector) {
    Icon(
        imageVector = imgVec,
        modifier = modifier.size(16.dp),
        contentDescription = null
    )
}

@Composable
fun NavItemText(name: String) {
    Text(
        text = name
    )

}
