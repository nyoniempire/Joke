package com.ankh.joker.data.remote

import androidx.lifecycle.LiveData
import com.ankh.joker.data.model.Joke
import com.ankh.joker.data.model.JokeEntity
import retrofit2.Response
import retrofit2.http.GET

interface JokerService {

    @GET("joke/Any?type=single")
    suspend fun getSingleJoke(): Response<JokeEntity>
}