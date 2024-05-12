package com.example.rick_morty_compose.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rick_morty_compose.R
import com.example.rick_morty_compose.domain.models.CharacterModel

@Composable
//fun DetailScreen(navController: NavController, idCharacter: Long, viewModel: DetailVM) {
fun DetailScreen(navController: NavController, viewModel: DetailVM) {

    val isLoading by viewModel.isLoading.observeAsState(true)
    val error by viewModel.error.observeAsState("")
    val character by viewModel.character.observeAsState()

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else if (error.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = error)
        }

    } else if (character != null) {
        CharacterBody(
            navController = navController,
            character = character!!,
            modifier = Modifier.fillMaxSize()
        )
    } else {
//        navController.popBackStack()
    }
}

@Composable
fun CharacterBody(
    navController: NavController,
    character: CharacterModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .width(150.dp)
        )
        Label(label = character.status)
        Text(
            text = character.name,
            maxLines = 2,
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = character.gender,
            maxLines = 1,
            fontStyle = FontStyle.Italic,
        )
        Text(
            text = "Especie: ${character.species}",
            maxLines = 1,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            textAlign = TextAlign.Start,
        )
        Text(
            text = "Lugar de origen: ${character.nameLocation}",
            maxLines = 1,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Start,
        )
        Text(
            text = "Ultima ubicacion: ${character.nameLocation}",
            maxLines = 1,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Start,
        )
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Atras")
        }
    }
}

@Composable
fun Label(label: String, modifier: Modifier = Modifier) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        modifier = modifier
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Green)
            .padding(horizontal = 12.dp, vertical = 6.dp),
    )
}