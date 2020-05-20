package com.example.cookingbook.network

import retrofit2.HttpException

const val API_KEY = "85df0209fb5c47ebbd8c7bc48cdf90ec"

class RecipesRepo(private val api: RecipesApi) {

    suspend fun fetchRandomRecipes(
        recipesQuantity: Int = 1,
        apiKey: String = API_KEY
    ): ResultWrapper<RecipesApiResponse> {
        return safeCall { api.fetchRandomRecipes(recipesQuantity, apiKey) }
    }
}

suspend fun <T> safeCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpException -> ResultWrapper.Failure(throwable.code(), throwable.message())
            else -> ResultWrapper.NetworkError(throwable)
        }
    }
}