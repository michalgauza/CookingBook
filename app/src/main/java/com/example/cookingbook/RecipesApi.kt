package com.example.cookingbook

import retrofit2.Response
import retrofit2.http.GET

interface RecipesApi {

    @GET("random?number=1&apiKey=85df0209fb5c47ebbd8c7bc48cdf90ec")
    suspend fun fetchRandomRecipes(): Response<String>
}