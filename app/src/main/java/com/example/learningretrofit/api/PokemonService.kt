package com.example.learningretrofit.api

import com.example.learningretrofit.model.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("api/v2/pokemon")
    suspend fun getPokemon(@Query("limit") limit: Int): Response<PokemonList>

    @GET("api/v2/pokemon/" + "{pokemon}")
    suspend fun getIndividualPokemon(@Path("pokemon") pokemon: String)
}