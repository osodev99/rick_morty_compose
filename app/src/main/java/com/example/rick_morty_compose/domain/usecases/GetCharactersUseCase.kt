package com.example.rick_morty_compose.domain.usecases

import com.example.rick_morty_compose.domain.models.CharacterModel
import com.example.rick_morty_compose.domain.repository.CharacterRepository
import com.example.rick_morty_compose.domain.repository.ResponseWrapper
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): ResponseWrapper<List<CharacterModel>> {
        delay(3000)
        return repository.getAllCharacters()
    }
}