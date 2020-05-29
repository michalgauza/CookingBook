package com.example.cookingbook.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("random")
    suspend fun fetchRandomRecipes(
        @Query("number") recipesQuantity: Int,
        @Query("apiKey") apiKey: String
    ): RecipesApiResponse

    @GET("search")
    suspend fun searchRecipes(
        @Query("cuisine") cuisine: String,
        @Query("diet") diet: String,
        @Query("intolerances") intolerances: String,
        @Query("offset") offset: Int,
        @Query("number")  number: Int,
        @Query("apiKey") apiKey: String
    ): SearchRecipeApiResponse
}