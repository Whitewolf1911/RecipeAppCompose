package com.alibasoglu.recipeapp.presentation

enum class FoodCategory(val value: String) {

    CHICKEN("Chicken"),
    BEEF("Dessert"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    MILK("Milk"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

fun getAllFoodCategories(): List<FoodCategory> {
    return FoodCategory.values().toList()
}

fun getFoodCategory(value: String): FoodCategory? {
    return try {
        FoodCategory.valueOf(value)
    } catch (e: Exception) {
        null
    }
}
