package com.example.learningretrofit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningretrofit.adapters.PokemonAdapter
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.api.RetrofitHelper
import com.example.learningretrofit.databinding.ActivityMainBinding
import com.example.learningretrofit.model.Result
import com.example.learningretrofit.repository.PokemonRepository
import com.example.learningretrofit.viewmodels.MainViewModel
import com.example.learningretrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pokemonRecyclerView = binding.rvPokemon
        pokemonRecyclerView.layoutManager = GridLayoutManager(this, 3)
        pokemonAdapter = PokemonAdapter()
        pokemonRecyclerView.adapter = pokemonAdapter

        val pokemonService = RetrofitHelper.getInstance().create(PokemonService::class.java)
        val pokemonRepository = PokemonRepository(pokemonService)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(pokemonRepository)
        )[MainViewModel::class.java]

        mainViewModel.pokemon.observe(this) {
            Log.d("HEMIL", it.results.toString())
            pokemonAdapter.submitList(it.results)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                pokemonAdapter.filterList(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
