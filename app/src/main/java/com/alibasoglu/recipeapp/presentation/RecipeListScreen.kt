package com.alibasoglu.recipeapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibasoglu.recipeapp.presentation.components.RecipeCard
import com.alibasoglu.recipeapp.presentation.components.SearchView

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val recipes = viewModel.state
    val searchQuery = viewModel.query

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            SearchView(
                onQueryChanged = viewModel::onQueryChanged, queryValue = searchQuery.value
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(recipes.list) { recipe ->
                    RecipeCard(recipe = recipe, onClick = {})
                }
            }
        }
    }
}
