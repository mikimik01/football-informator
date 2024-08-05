package com.example.footballmanager.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

@Composable
fun ButtonNavigationBar(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToCompetitionScreen: () -> Unit,
    onNavigateToNewsScreen: () -> Unit,
    onNavigateToAccountScreen: () -> Unit,
) {
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
                unselectedIconColor = colorResource(R.color.highlighted_element_color),
                unselectedTextColor = colorResource(R.color.highlighted_element_color),
                disabledIconColor = colorResource(R.color.highlighted_element_color),
                disabledTextColor = colorResource(R.color.highlighted_element_color)
            )
            NavigationBar(
                modifier = Modifier
                    .background(colorResource(R.color.highlighted_element_color)),
                containerColor = colorResource(R.color.highlighted_element_color),
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { onNavigateToHomeScreen.invoke() },
                    icon = { HomeNav() },
                    colors = buttonColor,
                    modifier = Modifier
                        .background(colorResource(R.color.highlighted_element_color))
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigateToCompetitionScreen.invoke() },
                    icon = { CompetitionNav() },
                    colors = buttonColor,
                    modifier = Modifier
                        .background(colorResource(R.color.highlighted_element_color))
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigateToNewsScreen.invoke() },
                    icon = { NewsNav() },
                    colors = buttonColor,
                    modifier = Modifier
                        .background(colorResource(R.color.highlighted_element_color))
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigateToAccountScreen.invoke() },
                    icon = { AccountNav() },
                    colors = buttonColor,
                    modifier = Modifier
                        .background(colorResource(R.color.highlighted_element_color))
                )
            }

        }
    }
}

@Composable
fun HomeNav(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 78.dp)
            .background(colorResource(R.color.highlighted_element_color))
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_icon),
            contentDescription = "fluent:home-24-filled",
            colorFilter = ColorFilter.tint(Color(0xfff63d68)),
            modifier = Modifier.requiredSize(size = 24.dp)
        )
        Text(
            text = "Home", color = Color.White, lineHeight = 1.43.em, style = TextStyle(
                fontSize = 14.sp, fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun CompetitionNav(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(color = colorResource(R.color.highlighted_element_color))
    ) {
        Image(
            painter = painterResource(id = R.drawable.competition_icon),
            contentDescription = "fluent:apps-list-24-regular",
            colorFilter = ColorFilter.tint(Color(0xff667085)),
            modifier = Modifier.requiredSize(size = 24.dp)
        )
        Text(
            text = "Competition",
            color = Color(0xff667085),
            lineHeight = 1.43.em,
            style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun NewsNav(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 78.dp)
            .background(color = colorResource(R.color.highlighted_element_color))
    ) {
        Image(
            painter = painterResource(id = R.drawable.news_icon),
            contentDescription = "fluent:news-24-regular",
            colorFilter = ColorFilter.tint(Color(0xff667085)),
            modifier = Modifier.requiredSize(size = 24.dp)
        )
        Text(
            text = "News", color = Color(0xff667085), lineHeight = 1.43.em, style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun AccountNav(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 78.dp)
            .background(color = colorResource(R.color.highlighted_element_color))
    ) {
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "mdi:account-outline",
            colorFilter = ColorFilter.tint(Color(0xff667085)),
            modifier = Modifier.requiredSize(size = 24.dp)
        )
        Text(
            text = "Account", color = Color(0xff667085), lineHeight = 1.43.em, style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}
