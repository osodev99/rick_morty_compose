package com.example.rick_morty_compose.domain.models

import com.example.rick_morty_compose.data.api.responses.CharacterResponse

data class CharacterModel(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val nameOrigin: String,
    val nameLocation: String,
    val imageUrl: String,
)

fun CharacterResponse.toModel() = CharacterModel(
    id,
    name,
    status,
    species,
    type,
    gender,
    origin.name,
    location.name,
    image
)