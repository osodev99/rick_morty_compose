package com.example.rick_morty_compose.data.api.responses

data class CharacterResponse(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationResponse,
    val location: LocationResponse,
    val image: String,
    val url: String,
    val created: String
)

data class LocationResponse(
    val name: String,
    val url: String
)
