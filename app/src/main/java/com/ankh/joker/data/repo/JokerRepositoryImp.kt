package com.ankh.joker.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankh.joker.data.remote.JokerService
import com.ankh.joker.domain.JokeState
import com.ankh.joker.domain.repo.IJokerRepository

class JokerRepositoryImp(private val service: JokerService) : IJokerRepository {

    private val jokeState = MutableLiveData<JokeState>()

    override fun getSingleJoke(): LiveData<JokeState> = jokeState

    override suspend fun launchSingleJoke() {
        jokeState.postValue(JokeState.Loading)
        when {
            service.getSingleJoke().isSuccessful -> {
                service.getSingleJoke().body()?.let { jokeEntity ->
                    jokeState.postValue(JokeState.Success(jokeEntity))
                }
            }
            else -> {
                jokeState.postValue(JokeState.Failure("Error"))
            }
        }
    }
}