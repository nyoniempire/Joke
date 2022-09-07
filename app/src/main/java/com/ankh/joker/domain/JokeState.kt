package com.ankh.joker.domain

import com.ankh.joker.data.model.Joke

sealed class JokeState{
    object Loading: JokeState()
    data class Success(val data: List<Joke>): JokeState()
    data class Failure(val error: String): JokeState()
}
