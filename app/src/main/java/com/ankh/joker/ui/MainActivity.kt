package com.ankh.joker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.ankh.joker.R
import com.ankh.joker.databinding.ActivityMainBinding
import com.ankh.joker.domain.JokeState
import com.ankh.joker.ui.viewmodel.JokerViewModel

class MainActivity : AppCompatActivity() {

    private val jokerViewModel = JokerViewModel()
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        jokerViewModel.jokeState.observe(this) { jokeState ->
            mainBinding.tvJoke.text =
                when (jokeState) {
                    is JokeState.Loading -> {
                        getString(R.string.txt_loading)
                    }
                    is JokeState.Success -> {
                        jokeState.data.joke
                    }
                    is JokeState.Failure -> {
                        getString(R.string.txt_failure)
                    }
                }

        }

        jokerViewModel.getJokes()

        mainBinding.btnNextJoke.setOnClickListener {
            jokerViewModel.getJokes()
        }
    }
}
