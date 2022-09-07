package com.ankh.joker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankh.joker.domain.repo.IJokerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokerViewModel @Inject constructor(private val repo: IJokerRepository) :
    ViewModel() {

    val jokeState = repo.getSingleJoke()

    fun getJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.launchSingleJoke()
        }
    }
}