package com.example.cookingbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class AllRecipesViewModel(private val repo: RecipesRepo) : ViewModel() {

    fun fetchRandomRecipes(){
        viewModelScope.launch {
            try {
                println(repo.fetchRandomRecipes())
            } catch (e: Exception){
                println(e)
            }
        }
    }
}
