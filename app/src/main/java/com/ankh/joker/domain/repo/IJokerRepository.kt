package com.ankh.joker.domain.repo

import androidx.lifecycle.LiveData
import com.ankh.joker.data.model.Joke
import com.ankh.joker.domain.JokeState

interface IJokerRepository {

    fun getSingleJoke(): LiveData<JokeState>

    suspend fun launchSingleJoke()
}