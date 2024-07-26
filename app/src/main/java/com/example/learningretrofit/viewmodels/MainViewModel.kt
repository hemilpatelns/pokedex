package com.example.learningretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningretrofit.model.PokemonList
import com.example.learningretrofit.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokemonRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPokemon(10000)
        }
    }

    val pokemon: LiveData<PokemonList>
        get() = repository.pokemon
}