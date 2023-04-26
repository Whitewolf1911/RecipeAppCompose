package com.alibasoglu.recipeapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val startColor = MaterialTheme.colorScheme.primary.copy(0.9f)
    val midColor = Color.White.copy(0.3f)
    val recipesState = viewModel.state
    val searchQuery = viewModel.query
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // icons to mimic drawer destinations
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    SearchView(
                        onQueryChanged = viewModel::onQueryChanged,
                        queryValue = searchQuery.value
                    )
                },
                bottomBar = {
                    BottomNavigationBar()
                },
                snackbarHost = {},
                floatingActionButton = {},
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxSize()
                    ) {
                        item {
                            LikeButton(sizeInDp = 80.dp)
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
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            // Don't wanna show this after adding shimmering
                            // CircularProgressBarWithText()
                        }
                    }
                }
            }
        }
    )


}

@Composable
fun BottomNavigationBar() {
    NavigationBar(windowInsets = WindowInsets.Companion.navigationBars) {
        NavigationBarItem(
            selected = true,
            alwaysShowLabel = false,
            onClick = {},
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = null) })
        NavigationBarItem(
            selected = false,
            onClick = {},
            alwaysShowLabel = false,
            label = { Text("Favorite") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) })
        NavigationBarItem(
            selected = false,
            onClick = {},
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) })
        NavigationBarItem(
            selected = false,
            onClick = {},
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) })
    }
}
