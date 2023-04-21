package com.alibasoglu.recipeapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibasoglu.recipeapp.presentation.components.CircularProgressBarWithText
import com.alibasoglu.recipeapp.presentation.components.RecipeCard
import com.alibasoglu.recipeapp.presentation.components.SearchView

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val recipesState = viewModel.state
    val searchQuery = viewModel.query
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
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
            items(recipesState.list) { recipe ->
                RecipeCard(recipe = recipe, onClick = {})
            }
        }
        if (recipesState.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressBarWithText()
            }
        }
    }
}
