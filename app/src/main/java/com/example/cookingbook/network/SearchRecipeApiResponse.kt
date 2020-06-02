package com.example.cookingbook.network

import com.example.cookingbook.models.RecipeModel

data class SearchRecipeApiResponse(
    val results: List<RecipeModel>,
    val baseUri: String,
    val offset: Int,
    val number: Int
)