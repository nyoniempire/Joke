package com.ankh.joker.data.model

data class JokesEntity(
    val error: Boolean,
    val amount: Long,
    val jokes: List<JokeEntity>
)