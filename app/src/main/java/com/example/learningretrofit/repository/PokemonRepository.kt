package com.example.learningretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.model.PokemonList

class PokemonRepository(private val pokemonService: PokemonService) {

    private val pokemonLiveData = MutableLiveData<PokemonList>()

    val pokemon: LiveData<PokemonList>
        get() = pokemonLiveData

    suspend fun getPokemon(limit: Int){
        val result = pokemonService.getPokemon(limit)
        if(result.body() != null){
            pokemonLiveData.postValue(result.body())
        }
    }
}