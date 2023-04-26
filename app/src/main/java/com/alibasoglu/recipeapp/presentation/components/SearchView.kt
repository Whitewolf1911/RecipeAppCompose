package com.alibasoglu.recipeapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.alibasoglu.recipeapp.presentation.getAllFoodCategories
import com.alibasoglu.recipeapp.ui.theme.localFont

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    queryValue: String,
) {
    var shouldShowCancelButton by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var textFieldFriction by remember { mutableStateOf(1f) }
    val focusManager = LocalFocusManager.current

    Column() {
        Row(
            modifier = modifier
                .fillMaxWidth(1f)
                .height(84.dp),
            Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                textStyle = MaterialTheme.localFont.regularH4,
                value = queryValue,
                onValueChange = { newValue ->
                    onQueryChanged(newValue)
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
                label = { Text("Search recipe...") },
                modifier = Modifier
                    .fillMaxWidth(textFieldFriction)
                    .clipScrollableContainer(orientation = Orientation.Horizontal)
                    .padding(8.dp)
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        shouldShowCancelButton = focusState.isFocused
                        textFieldFriction = if (shouldShowCancelButton) {
                            0.8f
                        } else {
                            1f
                        }
                    }
                    .animateContentSize(
                        animationSpec = tween(50, easing = LinearEasing)
                    ),
                shape = RoundedCornerShape(20.dp)
            )
            AnimatedVisibility(
                visible = shouldShowCancelButton,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    "Cancel",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .clickable {
                            onQueryChanged("")
                            shouldShowCancelButton = false
                            focusManager.clearFocus()
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            items(getAllFoodCategories()) { foodCategory ->
                FoodCategoryChip(
                    category = foodCategory.value,
                    onExecuteSearch = { onQueryChanged(foodCategory.value) }
                )
            }
        }
    }
}
