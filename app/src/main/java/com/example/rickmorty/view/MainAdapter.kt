package com.example.rickmorty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.data.Results
import com.example.rickmorty.databinding.AdapterCharacterBinding
import com.example.rickmorty.viewmodel.MainViewModel

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var characters = mutableListOf<Results>()

    fun setCharacterList(Char: List<Results>) {
        this.characters = characters.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterCharacterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val char = characters[position]
        holder.binding.name.text = char.name
        Glide.with(holder.itemView.context)
            .load(char.image)
            .into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return characters.size

    }
}

class MainViewHolder(val binding: AdapterCharacterBinding) : RecyclerView.ViewHolder(binding.root) {


}

