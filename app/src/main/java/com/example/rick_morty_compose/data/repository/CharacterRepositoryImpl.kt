package com.example.rick_morty_compose.data.repository

import com.example.rick_morty_compose.data.api.CharactersService
import com.example.rick_morty_compose.domain.models.CharacterModel
import com.example.rick_morty_compose.domain.models.toModel
import com.example.rick_morty_compose.domain.repository.CharacterRepository
import com.example.rick_morty_compose.domain.repository.ResponseWrapper
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharactersService
) : CharacterRepository {


    override suspend fun getAllCharacters(): ResponseWrapper<List<CharacterModel>> {
        return try {
            val response = service.getAllCharacters()
            if (response.isSuccessful) {
                val results = response.body()?.results ?: emptyList()
                ResponseWrapper.Success(results.map { it.toModel() })
            } else {
                ResponseWrapper.Error(response.code(), response.errorBody())
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> ResponseWrapper.NetworkError
                else -> ResponseWrapper.Error(error = t)
            }
        }
    }

    override suspend fun getCharacterById(id: Long): ResponseWrapper<CharacterModel> {
        return try {
            val response = service.getCharacterById(id)
            if (response.isSuccessful) {
                val result = response.body()
                ResponseWrapper.Success(result!!.toModel())
            } else {
                ResponseWrapper.Error(response.code(), response.errorBody())
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> ResponseWrapper.NetworkError
                else -> ResponseWrapper.Error(error = t)
            }
        }
    }
}