package com.example.rick_morty_compose.domain.usecases

import com.example.rick_morty_compose.domain.models.CharacterModel
import com.example.rick_morty_compose.domain.repository.CharacterRepository
import com.example.rick_morty_compose.domain.repository.ResponseWrapper
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(id: Long): ResponseWrapper<CharacterModel> {
        return repository.getCharacterById(id)
    }
}