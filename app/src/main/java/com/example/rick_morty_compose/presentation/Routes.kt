package com.example.rick_morty_compose.presentation

sealed class Routes(val route: String) {
    data object HomeScreen : Routes("home")
    data object DetailScreen : Routes("detail/{id}") {
        fun makeRoute(id: Long) = "detail/$id"
    }
}