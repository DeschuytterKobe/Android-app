package com.example.hogentderdezitapplicatie.api

import com.squareup.moshi.Json

data class JokeProperty(
    val id: String,

    @Json(name = "joke")
    val joke: String,

    )