package com.ankh.joker.domain.repo

import androidx.lifecycle.LiveData
import com.ankh.joker.domain.JokeState

interface IJokerRepository {

    fun getSingleJokes(): LiveData<JokeState>

    suspend fun launchSingleJokes()
}