package com.example.footballmanager.screens.result_screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.footballmanager.R

private const val ANIMATION_DURATION = 2000
private const val INITIAL_SCALE = 1.4f
private const val TARGET_SCALE = 1.5f

@Composable
fun ErrorScreen(errorHint: String) {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(R.string.remember_transition))
    val scale by infiniteTransition.animateFloat(
        initialValue = INITIAL_SCALE,
        targetValue = TARGET_SCALE,
        animationSpec = infiniteRepeatable(animation = keyframes {
            durationMillis = ANIMATION_DURATION
            TARGET_SCALE at ANIMATION_DURATION / 2
            INITIAL_SCALE at ANIMATION_DURATION
        }),
        label = stringResource(R.string.loading_resize_animation),
    )
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .scale(scale)
        ) {
            Text(
                text = stringResource(R.string.error),
                color = colorResource(id = R.color.headerDotColor),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = dimensionResource(id = R.dimen.header_font_size).value.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.wrapContentWidth()
            )
        }
        Text(
            text = errorHint,
            color = colorResource(id = R.color.headerDotColor),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = dimensionResource(id = R.dimen.header_font_size).value.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.wrapContentWidth()
        )
    }
}