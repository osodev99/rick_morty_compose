package com.example.rick_morty_compose.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rick_morty_compose.R
import com.example.rick_morty_compose.domain.models.CharacterModel
import com.example.rick_morty_compose.presentation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeVM) {
    val isLoading by viewModel.isLoading.observeAsState(true)
    val error by viewModel.error.observeAsState("")
    val characters by viewModel.characters.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Rick and Morty") })
        }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else if (error.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = error)
            }

        } else {
            ListCharacters(modifier = Modifier.padding(it), characters) {
                navController.navigate(Routes.DetailScreen.makeRoute(it))
            }
        }
    }
}

@Composable
fun ListCharacters(
    modifier: Modifier = Modifier,
    characters: List<CharacterModel>,
    onCharacterItemClick: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(characters.size) {
            CharacterItem(
                character = characters[it],
                onCharacterClick = { id -> onCharacterItemClick(id) }
            )
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterModel,
    onCharacterClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onCharacterClick(character.id) },
    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
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
                    .width(130.dp)
                    .height(130.dp)
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(16.dp),

                ) {
                Text(
                    text = character.name,
                    maxLines = 2,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = character.nameLocation,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = modifier
                        .padding(top = 4.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.Green)
                        .padding(horizontal = 12.dp, vertical = 6.dp),

                    )
            }
        }
    }
}

