package com.example.rick_morty_compose.domain.repository

import com.example.rick_morty_compose.domain.models.CharacterModel

interface CharacterRepository {
    suspend fun getAllCharacters(): ResponseWrapper<List<CharacterModel>>
    suspend fun getCharacterById(id: Long): ResponseWrapper<CharacterModel>
}
