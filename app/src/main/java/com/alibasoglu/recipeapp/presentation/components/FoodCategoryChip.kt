package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alibasoglu.recipeapp.ui.theme.localFont

@Composable
fun FoodCategoryChip(
    category: String,
    onExecuteSearch: () -> Unit,
) {
    Surface(
        modifier = Modifier.padding(horizontal = 4.dp),
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    onClick = {
                        onExecuteSearch()
                    }
                )
        ) {
            Text(
                text = category,
                style = MaterialTheme.localFont.semiBoldBody,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewFoodCategoryChip() {
    FoodCategoryChip(category = "Vegan", onExecuteSearch = {})
}
