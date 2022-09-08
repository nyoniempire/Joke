package com.ankh.joker.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankh.joker.R
import com.ankh.joker.data.model.Joke
import com.ankh.joker.databinding.ActivityMainBinding
import com.ankh.joker.domain.JokeState
import com.ankh.joker.ui.adapter.JokesAdapter
import com.ankh.joker.ui.viewmodel.JokerViewModel
import com.ankh.joker.util.CheckInternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val jokerViewModel: JokerViewModel by viewModels()

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

    override fun onResume() {
        super.onResume()
        CheckInternetConnection(this).apply {
            if (!isInternetConnected())
                showErrorSnackBar(mainBinding.root)

        }
    }

    private fun setupAdapter(jokes: List<Joke>) {
        mainBinding.rvJokes.apply {
            adapter = JokesAdapter(jokes)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}
