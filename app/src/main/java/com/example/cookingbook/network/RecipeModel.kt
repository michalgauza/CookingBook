package com.example.cookingbook.network

data class RecipeModel(
    val id: Int,
    val title: String,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val readyInMinutes: Int,
    var image: String,
    val summary: String,
    val instruction: String
)