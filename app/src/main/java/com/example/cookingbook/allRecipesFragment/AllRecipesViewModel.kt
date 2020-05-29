package com.example.cookingbook.allRecipesFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingbook.network.RecipesApiResponse
import com.example.cookingbook.network.RecipesRepo
import com.example.cookingbook.network.SearchRecipeApiResponse
import com.example.cookingbook.network.handleResponse
import kotlinx.coroutines.launch

const val RECIPE_IMAGE_BASE_URL = "https://spoonacular.com/recipeImages/"

class AllRecipesViewModel(private val repo: RecipesRepo) : ViewModel() {

    private val _randomRecipesLiveData = MutableLiveData<RecipesApiResponse>()
    val randomRecipesLiveData: LiveData<RecipesApiResponse>
        get() = _randomRecipesLiveData

    private val _searchRecipesLiveData = MutableLiveData<SearchRecipeApiResponse>()
    val searchRecipesLiveData: LiveData<SearchRecipeApiResponse>
        get() = _searchRecipesLiveData

    fun fetchRandomRecipes() {
        viewModelScope.launch {
            repo.fetchRandomRecipes(10).handleResponse(
                doOnSuccess = { response ->
                    _randomRecipesLiveData.value = response
                },
                doOnFailure = { code -> println("Error $code") },
                doOnError = { println("Error: cannot connect") })
        }
    }

    fun searchRecipes() {
        viewModelScope.launch {
            repo.searchRecipes(offset = 0, number = 20).handleResponse(
                doOnSuccess = { response ->
                    response.results.forEach { recipe ->
                        recipe.image = RECIPE_IMAGE_BASE_URL + recipe.image
                    }
                    _searchRecipesLiveData.value = response
                },
                doOnFailure = { code -> println("Error $code") },
                doOnError = { println("Error: cannot connect") })
        }
    }
}
