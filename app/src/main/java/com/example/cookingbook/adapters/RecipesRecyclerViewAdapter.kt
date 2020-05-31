package com.example.cookingbook.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingbook.databinding.RecipeCardBinding
import com.example.cookingbook.network.RecipeModel
import com.example.cookingbook.utils.RECIPES_DIFF_CALLBACK

class RecipesRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RecipeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    private val recipesDiffer = AsyncListDiffer(this, RECIPES_DIFF_CALLBACK)

    var longClickCallback: ((Int) -> Unit)? = null

    override fun getItemCount(): Int = recipesDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecipesViewHolder -> holder.bind(
                recipesDiffer.currentList[position],
                longClickCallback
            )
        }
    }

    fun submitList(list: List<RecipeModel>) {
        recipesDiffer.submitList(list)
    }
}

class RecipesViewHolder(private val binding: RecipeCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        recipe: RecipeModel,
        longClickCallback: ((Int) -> Unit)?
    ) {
        binding.recipe = recipe
        binding.recipeCardCardView.setOnLongClickListener {
            longClickCallback?.invoke(recipe.id)
            return@setOnLongClickListener true
        }
    }
}