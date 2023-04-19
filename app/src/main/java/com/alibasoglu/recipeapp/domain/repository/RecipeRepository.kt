package com.alibasoglu.recipeapp.domain.repository

import com.alibasoglu.recipeapp.domain.model.Recipe

interface RecipeRepository {

    suspend fun searchRecipe(token: String, page: Int, query: String): List<Recipe>

    suspend fun getRecipe(token: String, id: Int): Recipe
}
