package com.example.rick_morty_compose.di

import com.example.rick_morty_compose.data.api.CharactersService
import com.example.rick_morty_compose.data.repository.CharacterRepositoryImpl
import com.example.rick_morty_compose.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCharacterService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

    @Provides
    @Singleton
    fun providerCharacterRepository(charactersService: CharactersService): CharacterRepository {
        return CharacterRepositoryImpl(charactersService)
    }
}

