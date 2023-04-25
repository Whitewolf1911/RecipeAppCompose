package com.alibasoglu.recipeapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibasoglu.recipeapp.presentation.components.LikeButton
import com.alibasoglu.recipeapp.presentation.components.RecipeCard
import com.alibasoglu.recipeapp.presentation.components.SearchView
import com.alibasoglu.recipeapp.presentation.components.ShimmerRecipeCardItem

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val startColor = MaterialTheme.colorScheme.primary.copy(0.9f)
    val midColor = Color.White.copy(0.3f)
    val recipesState = viewModel.state
    val searchQuery = viewModel.query
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
            item {
                LikeButton()
            }
            item {
                TopAppBar(
                    title = { Text("Recipe App") }
                )
            }
            item {
                SearchView(
                    onQueryChanged = viewModel::onQueryChanged,
                    queryValue = searchQuery.value
                )
            }
            item { Spacer(modifier = Modifier.height(4.dp)) }
            if (recipesState.isLoading) {
                items(4) {
                    ShimmerRecipeCardItem(
                        colors = listOf(
                            startColor,
                            midColor,
                            startColor
                        ),
                        cardHeight = 280.dp,
                        cardWidth = deviceWidth,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    ShimmerRecipeCardItem(
                        colors = listOf(
                            startColor,
                            midColor,
                            startColor
                        ),
                        cardHeight = 60.dp,
                        cardWidth = deviceWidth,
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
            if (recipesState.isLoading.not()) {
                items(recipesState.list) { recipe ->
                    RecipeCard(recipe = recipe, onClick = {})
                }
            }
        }
        if (recipesState.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                // Don't wanna show this after adding shimmering
                // CircularProgressBarWithText()
            }
        }
    }
}
