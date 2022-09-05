package com.ankh.joker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankh.joker.data.remote.JokerClient
import com.ankh.joker.data.remote.JokerService
import com.ankh.joker.data.repo.JokerRepositoryImp
import com.ankh.joker.domain.JokeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokerViewModel : ViewModel() {

    //inject service
    private val service = JokerClient.getClient().create(JokerService::class.java)
    private val repo = JokerRepositoryImp(service)

    val jokeState = repo.getSingleJoke()

    fun getJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.launchSingleJoke()
        }
    }
}