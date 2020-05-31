package com.example.cookingbook.allRecipesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cookingbook.R
import com.example.cookingbook.adapters.RecipesRecyclerViewAdapter
import com.example.cookingbook.network.RecipesApiResponse
import com.example.cookingbook.network.SearchRecipeApiResponse
import kotlinx.android.synthetic.main.all_recpies_fragment.*
import org.koin.android.ext.android.inject


class AllRecipesFragment : Fragment() {

    private val viewModel by inject<AllRecipesViewModel>()

    private val randomRecipesObserver = Observer<RecipesApiResponse> { response ->
        recyclerViewAdapter.submitList(response.recipes)
    }

    private val searchRecipesObserver = Observer<SearchRecipeApiResponse> { response ->
        recyclerViewAdapter.submitList(response.results)
    }

    private lateinit var recyclerViewAdapter: RecipesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_recpies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.randomRecipesLiveData.observe(viewLifecycleOwner, randomRecipesObserver)
        viewModel.searchRecipesLiveData.observe(viewLifecycleOwner, searchRecipesObserver)
        //todo data binding
        button.setOnClickListener {
            viewModel.searchRecipes()
        }
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter =
            RecipesRecyclerViewAdapter()
        recyclerViewAdapter.longClickCallback = { recipeId ->
            println(recipeId)
        }
        //todo data binding
        all_recipes_fragment_recycler_view.adapter = recyclerViewAdapter
        all_recipes_fragment_recycler_view.layoutManager = GridLayoutManager(context, 2)
    }
}
