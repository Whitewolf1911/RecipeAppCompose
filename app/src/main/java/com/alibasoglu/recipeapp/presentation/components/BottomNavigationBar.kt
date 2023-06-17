package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar() {
    NavigationBar(windowInsets = WindowInsets.navigationBars) {
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

@Preview
@Composable
fun PreviewBottomNavBar() {
    BottomNavigationBar()
}
