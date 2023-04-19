package com.alibasoglu.recipeapp.data.remote.model

import com.alibasoglu.recipeapp.domain.model.Recipe
import com.google.gson.annotations.SerializedName

data class RecipeResponse(

    @SerializedName("pk")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("publisher")
    val publisher: String? = null,

    @SerializedName("rating")
    val rating: Int? = null,

    @SerializedName("sourceUrl")
    val sourceUrl: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("cooking_instructions")
    val cookingInstructions: String? = null,

    @SerializedName("ingredients")
    val ingredients: List<String>? = null,

    @SerializedName("featured_image")
    val featuredImage: String? = null,

    @SerializedName("date_added")
    val dateAdded: String? = null,

    @SerializedName("date_updated")
    val dateUpdated: String? = null,
)

fun RecipeResponse.mapToRecipe(): Recipe {
    return Recipe(
        id = id,
        title = title,
        publisher = publisher,
        featuredImage = featuredImage,
        rating = rating,
        sourceUrl = sourceUrl,
        description = description,
        cookingInstructions = cookingInstructions,
        ingredients = ingredients,
        dateAdded = dateAdded,
        dateUpdated = dateUpdated
    )
}
