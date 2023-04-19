package com.alibasoglu.recipeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val recipes: List<RecipeResponse>
)
