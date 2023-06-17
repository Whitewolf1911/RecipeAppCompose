package com.alibasoglu.recipeapp.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alibasoglu.recipeapp.presentation.components.BottomNavigationBar
import com.alibasoglu.recipeapp.presentation.components.LikeButton
import com.alibasoglu.recipeapp.presentation.components.RecipeCard
import com.alibasoglu.recipeapp.presentation.components.SearchView
import com.alibasoglu.recipeapp.presentation.components.ShimmerRecipeCardItem
import kotlinx.coroutines.launch

@Composable
fun RecipeListScreen(
    // Warning : Do not pass viewModels to screens ! pass state instead.
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val startColor = MaterialTheme.colorScheme.primary.copy(0.9f)
    val midColor = Color.White.copy(0.3f)
    val recipesState = viewModel.state
    val searchQuery = viewModel.query
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.stackoverflow.com"))
    val context = LocalContext.current
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
                        item {
                            Button(onClick = {
                                context.startActivity(intent)
                            }) {
                                Text("click to open url")
                            }
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
//                    if (recipesState.isLoading) {
//                        Box(
//                            contentAlignment = Alignment.Center,
//                            modifier = Modifier.fillMaxSize()
//                        ) {
//                            // Don't wanna show this after adding shimmering
//                             CircularProgressBarWithText()
//                        }
//                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun Test() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Scroll Behavior Test") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            items((1..50).toList()) { item ->
                Text(modifier = Modifier.padding(8.dp), text = "Item $item")
            }
        }
    }
}
