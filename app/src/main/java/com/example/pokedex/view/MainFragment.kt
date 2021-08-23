package com.example.pokedex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.databinding.MainFragmentBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val adapter = PokemonAdapter()

    private val observerPokemons = Observer<List<Pokemon>> { pokemons ->
        adapter.update(pokemons)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.pokeListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.pokeListRecycler.adapter = adapter


        viewModel.pokeResponse.observe(viewLifecycleOwner, observerPokemons)
        viewModel.fetchAllFromDatabase(requireContext())

    }

}