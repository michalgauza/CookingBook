package com.example.cookingbook.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("random")
    suspend fun fetchRandomRecipes(
        @Query("number") recipesQuantity: Int,
        @Query("apiKey") apiKey: String
    ): RecipesApiResponse
}