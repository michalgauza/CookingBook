package com.example.cookingbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingbook.network.RecipesApiResponse
import com.example.cookingbook.network.RecipesRepo
import com.example.cookingbook.network.handleResponse
import kotlinx.coroutines.launch

class AllRecipesViewModel(private val repo: RecipesRepo) : ViewModel() {

    private val _randomRecipesLiveData = MutableLiveData<RecipesApiResponse>()
    val randomRecipesLiveData: LiveData<RecipesApiResponse>
        get() = _randomRecipesLiveData

    fun fetchRandomRecipes() {
        viewModelScope.launch {
            repo.fetchRandomRecipes(10).handleResponse(
                doOnSuccess = { recipeApiResponse ->
                    _randomRecipesLiveData.value = recipeApiResponse
                },
                doOnFailure = { code -> println("Error $code") },
                doOnError = { println("Error: cannot connect") })
        }
    }
}
