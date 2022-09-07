package com.ankh.joker.data.model

data class Joke(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String,
    val flags: Flags,
    val id: Long,
    val safe: Boolean,
    val lang: String
)