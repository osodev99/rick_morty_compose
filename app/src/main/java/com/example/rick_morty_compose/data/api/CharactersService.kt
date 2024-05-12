package com.example.rick_morty_compose.data.api

import com.example.rick_morty_compose.data.api.responses.AllCharactersResponse
import com.example.rick_morty_compose.data.api.responses.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {
    @GET("character")
    suspend fun getAllCharacters(): Response<AllCharactersResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Long): Response<CharacterResponse>
}