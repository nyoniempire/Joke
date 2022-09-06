package com.ankh.joker.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankh.joker.R
import com.ankh.joker.data.model.JokeEntity
import com.ankh.joker.databinding.ActivityMainBinding
import com.ankh.joker.domain.JokeState
import com.ankh.joker.ui.adapter.JokesAdapter
import com.ankh.joker.ui.viewmodel.JokerViewModel

class MainActivity : AppCompatActivity() {

    private val jokerViewModel = JokerViewModel()
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        jokerViewModel.jokeState.observe(this) { jokeState ->
            when (jokeState) {
                is JokeState.Loading -> {
                    mainBinding.pbJokes.visibility = View.VISIBLE
                }
                is JokeState.Success -> {
                    setupAdapter(jokeState.data)
                    mainBinding.pbJokes.visibility = View.GONE
                }
                is JokeState.Failure -> {
                    mainBinding.pbJokes.visibility = View.GONE
                }
            }

        }
        jokerViewModel.getJokes()

        mainBinding.btnRefresh.setOnClickListener {
            jokerViewModel.getJokes()
        }
    }

    private fun setupAdapter(jokes: List<JokeEntity>) {
        mainBinding.rvJokes.apply {
            adapter = JokesAdapter(jokes)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}
