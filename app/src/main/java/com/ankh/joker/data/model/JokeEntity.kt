package com.ankh.joker.data.model

data class JokeEntity(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String,
    val flags: FlagsEntity,
    val id: Long,
    val safe: Boolean,
    val lang: String
)