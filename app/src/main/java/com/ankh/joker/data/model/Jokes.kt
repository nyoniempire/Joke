package com.ankh.joker.data.model

data class Jokes(
    val error: Boolean,
    val amount: Long,
    val jokes: List<Joke>
)