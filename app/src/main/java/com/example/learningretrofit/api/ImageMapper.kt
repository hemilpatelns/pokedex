package com.example.learningretrofit.api

import com.example.learningretrofit.model.Result

class ImageMapper(private val list: List<Result>) {
    private val imageUrls = mutableListOf<String>()
    fun getImageUrls() {
        for (i in 1..list.size) {
            imageUrls.add("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$i.png")
        }
    }

    fun getImageUrlById(id: Int): String {
        return imageUrls[id]
    }
}