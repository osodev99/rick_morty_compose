package com.example.rick_morty_compose.domain.repository

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class Error(val code: Int? = null, val error: Any? = null) :
        ResponseWrapper<Nothing>()

    data object NetworkError : ResponseWrapper<Nothing>()
}