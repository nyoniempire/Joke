package com.ankh.joker.di

import com.ankh.joker.data.remote.JokerService
import com.ankh.joker.data.repo.JokerRepositoryImp
import com.ankh.joker.domain.repo.IJokerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient() = OkHttpClient.Builder().build()


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = "https://v2.jokeapi.dev/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesJokerService(retrofit: Retrofit): JokerService = retrofit.create(JokerService::class.java)

    @Provides
    fun providesJokesRepository(service: JokerService): IJokerRepository =
        JokerRepositoryImp(service)
}