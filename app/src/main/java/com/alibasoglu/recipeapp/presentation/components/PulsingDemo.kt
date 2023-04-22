package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PulsingDemo() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(color)
    )
}

@Composable
fun ResizingAnimatingBox() {

    Box(
        modifier = Modifier
            .height(200.dp)
            .width(getAnimationMagnitude(initial = 200.dp, final = 1000.dp))
            .background(Color.Red)
    )
}

@Composable
fun getAnimationMagnitude(initial: Dp, final: Dp): Dp {
    val infiniteTransition = rememberInfiniteTransition()
    val width by infiniteTransition.animateValue(
        initialValue = initial,
        targetValue = final,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInCubic),
            repeatMode = RepeatMode.Reverse
        )
    )
    return width
}


@Preview
@Composable
fun PreviewPulsing() {
    ResizingAnimatingBox()
}
