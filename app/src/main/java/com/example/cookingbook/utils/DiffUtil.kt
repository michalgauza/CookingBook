package com.example.cookingbook.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.cookingbook.network.RecipeModel

val RECIPES_DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipeModel>() {

    override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean =
        oldItem == newItem

}