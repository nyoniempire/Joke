package com.ankh.joker.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JokerClient {

    fun getClient(): Retrofit {
        val client = OkHttpClient.Builder().build()
        val baseUrl = "https://v2.jokeapi.dev/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}