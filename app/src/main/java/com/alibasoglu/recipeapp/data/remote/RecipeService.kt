package com.alibasoglu.recipeapp.data.remote

import com.alibasoglu.recipeapp.data.remote.model.RecipeResponse
import com.alibasoglu.recipeapp.data.remote.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    suspend fun searchRecipe(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String,
    ): RecipeSearchResponse

    @GET("get")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
    ): RecipeResponse

}
