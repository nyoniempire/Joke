package com.ankh.joker.data.remote

import com.ankh.joker.data.model.JokesEntity
import retrofit2.Response
import retrofit2.http.GET

interface JokerService {

    @GET("joke/Any?type=single&amount=10")
    suspend fun getJokes(): Response<JokesEntity>
}