package com.alibasoglu.recipeapp.presentation.model

import com.alibasoglu.recipeapp.domain.model.Recipe

data class RecipeListState(
    val list: List<Recipe> = emptyList()
)
