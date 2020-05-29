package com.example.cookingbook.network

data class SearchRecipeApiResponse(
    val results: List<RecipeModel>,
    val baseUri: String,
    val offset: Int,
    val number: Int
)