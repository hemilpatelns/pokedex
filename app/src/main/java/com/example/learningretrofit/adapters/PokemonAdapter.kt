package com.example.learningretrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.learningretrofit.R
import com.example.learningretrofit.api.ImageMapper
import com.example.learningretrofit.databinding.LayoutPokemonCardsBinding
import com.example.learningretrofit.model.Result
import java.util.Locale

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var originalPokeList: List<Result> = emptyList()
    private var filteredPokeList: List<Result> = emptyList()

    fun capitalizeFirstLetter(text: String): String {
        return text.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

    inner class PokemonViewHolder(private val binding: LayoutPokemonCardsBinding) :
        ViewHolder(binding.root) {
        fun bind(poke: Result) {
            binding.tvPokemonName.text = capitalizeFirstLetter(poke.name)
            val splitUrls = poke.url.split("/")
            val pos = splitUrls[splitUrls.size - 2]
            val uri =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pos.png"
            Glide.with(binding.ivPokemon.context)
                .load(uri)
                .into(binding.ivPokemon)
        }
    }

    fun submitList(poke: List<Result>) {
        originalPokeList = poke
        filteredPokeList = poke
        notifyDataSetChanged()
    }

    fun filterList(query: String) {
        filteredPokeList = if (query.isEmpty()) {
            originalPokeList
        } else {
            originalPokeList.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view =
            LayoutPokemonCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredPokeList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val poke = filteredPokeList[position]
        holder.bind(poke)
    }
}
