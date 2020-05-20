package com.example.cookingbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingbook.network.RecipesApiResponse
import kotlinx.android.synthetic.main.all_recpies_fragment.*
import org.koin.android.ext.android.inject


class AllRecipesFragment : Fragment() {

    private val viewModel by inject<AllRecipesViewModel>()

    private val randomRecipesObserver = Observer<RecipesApiResponse> { recipesApiResponse ->
        recyclerViewAdapter.submitList(recipesApiResponse.recipes)
    }

    lateinit var recyclerViewAdapter: RecipesRecyclerViewAdapter

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
        //todo data binding
        button.setOnClickListener {
            viewModel.fetchRandomRecipes()
        }
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = RecipesRecyclerViewAdapter()
        //todo data binding
        all_recipes_fragment_recycler_view.adapter = recyclerViewAdapter
        all_recipes_fragment_recycler_view.layoutManager = LinearLayoutManager(context)
    }
}
