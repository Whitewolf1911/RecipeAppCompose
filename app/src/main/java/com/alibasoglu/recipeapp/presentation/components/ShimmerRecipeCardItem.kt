package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeCardItem(
    colors: List<Color>,
    cardHeight: Dp,
    cardWidth: Dp,
    modifier: Modifier = Modifier
) {
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = Offset(
            getShimmerAnimationMagnitude(finalTargetValue = cardWidth),
            getShimmerAnimationMagnitude(finalTargetValue = cardHeight)
        )
    )
    Surface(color = MaterialTheme.colorScheme.background) {
        Spacer(
            modifier = modifier
                .width(cardWidth)
                .height(cardHeight)
                .background(brush)
        )
    }
}

@Composable
private fun getShimmerAnimationMagnitude(finalTargetValue: Dp): Float {
    val infiniteTransition = rememberInfiniteTransition()
    val dimensionValue by infiniteTransition.animateValue(
        initialValue = 0.dp,
        targetValue = finalTargetValue.times(10),
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    return dimensionValue.value
}

@Preview
@Composable
fun PreviewShimmer() {
    val startColor = MaterialTheme.colorScheme.primary.copy(0.9f)
    val midColor = Color.LightGray.copy(0.1f)
    ShimmerRecipeCardItem(
        colors = listOf(
            startColor,
            midColor,
            startColor,
        ),
        cardHeight = 400.dp,
        cardWidth = 400.dp
    )
}
