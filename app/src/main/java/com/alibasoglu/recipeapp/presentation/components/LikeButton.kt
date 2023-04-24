package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LikeButton() {
    val animationDuration = 600
    val isFavorite = remember { mutableStateOf(false) }
    val size = remember { androidx.compose.animation.core.Animatable(0.8f) }
    val color = remember { Animatable(Color.White) }
    val scope = rememberCoroutineScope()
    Icon(
        tint = color.value,
        imageVector = Icons.Default.Favorite,
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = {
                scope.launch {
                    if (isFavorite.value) {
                        launch() {
                            color.animateTo(Color.White, animationSpec = tween(animationDuration))
                        }
                        launch() {
                            size.animateTo(0.8f, animationSpec = tween(animationDuration))
                        }
                        isFavorite.value = false
                    } else {
                        launch() {
                            size.animateTo(1f, animationSpec = tween(animationDuration))
                        }
                        launch {
                            color.animateTo(Color.Red, animationSpec = tween(animationDuration))
                        }
                        isFavorite.value = true
                    }
                }
            })
            .size(size.value.dp * 200)
    )
}

@Preview
@Composable
fun PreviewLikeButton() {
    LikeButton()
}
