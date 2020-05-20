package com.example.cookingbook

data class RecipeModel(
    val id: Int,
    val title: String,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val readyInMinutes: Int,
    val image: String,
    val summary: String,
    val instruction: String
)