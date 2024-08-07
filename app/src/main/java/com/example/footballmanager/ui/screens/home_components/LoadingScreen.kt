package com.example.footballmanager.ui.screens.home_components


import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.footballmanager.R

private const val ANIMATION_DURATION = 1500
private const val INITIAL_SCALE = 0.5f
private const val TARGET_SCALE = 1.5f

@Composable
fun LoadingScreen() {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(R.string.remember_transition))
    val scale by infiniteTransition.animateFloat(
        initialValue = INITIAL_SCALE,
        targetValue = TARGET_SCALE,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_DURATION
                TARGET_SCALE at ANIMATION_DURATION / 2
                INITIAL_SCALE at ANIMATION_DURATION
            }
        ),
        label = stringResource(R.string.loading_resize_animation),
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.loading_animation_size))
                .scale(scale)
                .background(colorResource(id = R.color.app_red_motive), shape = CircleShape),
        )
    }
}