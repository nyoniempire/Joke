package com.ankh.joker.domain

import com.ankh.joker.data.model.JokeEntity

sealed class JokeState{
    object Loading: JokeState()
    data class Success(val data: JokeEntity): JokeState()
    data class Failure(val error: String): JokeState()
}
