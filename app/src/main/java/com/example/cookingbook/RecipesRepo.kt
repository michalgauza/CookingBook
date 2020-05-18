package com.example.cookingbook

import retrofit2.Response

class RecipesRepo(private val api: RecipesApi){

    suspend fun fetchRandomRecipes(): Response<String> = api.fetchRandomRecipes()
}