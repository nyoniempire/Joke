package com.ankh.joker.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankh.joker.data.remote.JokerService
import com.ankh.joker.domain.JokeState
import com.ankh.joker.domain.repo.IJokerRepository

class JokerRepositoryImp(private val service: JokerService) : IJokerRepository {

    val jokeState = MutableLiveData<JokeState>()

    override fun getSingleJokes(): LiveData<JokeState> = jokeState

    override suspend fun launchSingleJokes() {
        jokeState.postValue(JokeState.Loading)
        when {
            service.getJokes().isSuccessful -> {
                service.getJokes().body()?.let { jokes->
                    jokeState.postValue(JokeState.Success(jokes.jokes))
                }
            }
            else -> {
                jokeState.postValue(JokeState.Failure("Error"))
            }
        }
    }
}