package com.ankh.joker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ankh.joker.R
import com.ankh.joker.data.model.JokeEntity
import com.google.android.material.textview.MaterialTextView

class JokesAdapter(private val jokes: List<JokeEntity>) :
    RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    class JokesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvJoke = itemView.findViewById<MaterialTextView>(R.id.tv_joke)
        fun bind(joke: JokeEntity) {
            tvJoke.text = joke.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.joke_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int {
        return jokes.size
    }
}