package com.example.learningretrofit.model

data class PokemonList(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)