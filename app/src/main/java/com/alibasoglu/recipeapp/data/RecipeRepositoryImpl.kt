package com.alibasoglu.recipeapp.data

import com.alibasoglu.recipeapp.data.remote.RecipeService
import com.alibasoglu.recipeapp.data.remote.model.mapToRecipe
import com.alibasoglu.recipeapp.domain.model.Recipe
import com.alibasoglu.recipeapp.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val recipeService: RecipeService
) : RecipeRepository {
    override suspend fun searchRecipe(token: String, page: Int, query: String): List<Recipe> {
        return recipeService.searchRecipe(token = token, page = page, query = query).recipes.map {
            it.mapToRecipe()
        }
    }

    override suspend fun getRecipe(token: String, id: Int): Recipe {
        return recipeService.getRecipe(token = token, id = id).mapToRecipe()
    }
}
