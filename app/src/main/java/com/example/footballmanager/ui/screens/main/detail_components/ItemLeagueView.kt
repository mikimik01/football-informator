package com.example.footballmanager.ui.screens.main.detail_components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R
import com.example.footballmanager.ui.MasterViewModel
import com.example.footballmanager.ui.providers.Providers
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveItemElements
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveItemUpperBody
import com.example.footballmanager.ui.screens.main.home_components.success_components.live_score_components.LiveScoreItem

@Composable
fun DetailItemView(
    modifier: Modifier = Modifier,
    selectedItemData: LiveItemElements) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .requiredWidth(width = 358.dp)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xff1e1e1e))
                .padding(all = 12.dp)
        ) {
            LiveItemUpperBody(
                selectedItemData,
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(), color = Color(0xff3a3a3a)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                    modifier = Modifier
                        .requiredWidth(width = 88.dp)
                ) {
                    Text(
                        text = "Goal Scorer",
                        color = Color(0xff1e1e1e),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp))
                }
                Image(
                    painter = painterResource(id = R.drawable.ball_icon),
                    contentDescription = "fluent:sport-soccer-24-filled",
                    colorFilter = ColorFilter.tint(Color(0xff5d5c64)),
                    modifier = Modifier
                        .requiredSize(size = 16.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Antony 32’",
                        color = Color(0xffb6b6b6),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp))
                    Text(
                        text = "Diogo Dalot 76’",
                        color = Color(0xffb6b6b6),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.5.em,
                        style = TextStyle(
                            fontSize = 12.sp))
                }
            }
        }
    }
}

@Composable
fun Property1Live(modifier: Modifier = Modifier) {
    Row(modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color(0xffecfdf3))
                .padding(
                    start = 6.dp,
                    end = 8.dp,
                    top = 2.dp,
                    bottom = 2.dp
                )
        ) {
            Sizesm()
            Text(
                text = "78",
                color = Color(0xff027a48),
                textAlign = TextAlign.Center,
                lineHeight = 1.5.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium))
        }
    }
}

@Composable
fun Sizesm(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredSize(size = 8.dp)
    ) {
        Badge(
            containerColor = Color(0xff12b76a),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 1.dp,
                    y = 1.dp
                ))
    }
}

