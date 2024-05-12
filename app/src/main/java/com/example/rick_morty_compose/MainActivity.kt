package com.example.rick_morty_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rick_morty_compose.presentation.Routes
import com.example.rick_morty_compose.presentation.detail.DetailScreen
import com.example.rick_morty_compose.presentation.detail.DetailVM
import com.example.rick_morty_compose.presentation.home.HomeScreen
import com.example.rick_morty_compose.presentation.home.HomeVM
import com.example.rick_morty_compose.presentation.theme.Rick_Morty_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeVM: HomeVM by viewModels()
    private val detailVM: DetailVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rick_Morty_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.HomeScreen.route,
                    ) {
                        composable(Routes.HomeScreen.route) { HomeScreen(navController, homeVM) }
                        composable(
                            Routes.DetailScreen.route,
                            arguments = listOf(navArgument("id") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getLong("id") ?: 1
                            detailVM.getCharacter(id)
                            DetailScreen(navController, detailVM)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Pedir data")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Rick_Morty_ComposeTheme {
        Greeting("Android")
    }
}