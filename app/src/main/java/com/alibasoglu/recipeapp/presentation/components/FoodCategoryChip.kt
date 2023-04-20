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


@Composable
fun FoodCategoryChip(
    category: String,
    onExecuteSearch: () -> Unit
) {
    Surface(
        modifier = Modifier.padding(horizontal = 4.dp),
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.clickable(onClick = { onExecuteSearch.invoke() })
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }


}


@Preview
@Composable
fun PreviewFoodCategoryChip() {

    FoodCategoryChip(category = "Vegan", onExecuteSearch = {})
}
