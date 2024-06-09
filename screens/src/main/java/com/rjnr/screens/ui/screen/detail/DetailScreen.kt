package com.rjnr.screens.ui.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.screen.composeExt.onScreenStart
import com.rjnr.screens.ui.screen.list.Loading

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    screen: DetailScreen,
    viewmodel: DetailsViewModel = DetailsViewModel(),
) {
    val details = viewmodel.uiState()
    val navigation = navigation()
    onScreenStart {
        viewmodel.start(screen.id)
    }

    DetailUI(modifier, details)

    BackHandler {
        navigation.onBackPressed()
    }
}

@Composable
private fun DetailUI(
    modifier: Modifier,
    uiState: DetailState,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (uiState.loading) {
            Loading()
        }

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CharacterHeader(
                name = uiState.character.name,
                image = uiState.character.image,
                species = uiState.character.species,
            )
            Divider(
                modifier =
                    Modifier
                        .padding(horizontal = 24.dp),
                color = Color.LightGray,
                thickness = 1.dp,
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Text(
            text = uiState.character.name,
        )
    }
}

@Composable
private fun CharacterHeader(
    image: String,
    name: String,
    species: String,
) {
    Card(
        modifier =
            Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(50.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Gray),
        shape = RoundedCornerShape(50.dp),
    ) {
        AsyncImage(
            model = image,
            contentDescription = "character header",
            modifier =
                Modifier
                    .size(100.dp)
                    .clip(CircleShape),
        )
    }
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White,
    )

    Text(
        text = species,
        style = MaterialTheme.typography.titleSmall,
    )
    Spacer(modifier = Modifier.height(12.dp))
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPrev() {
    DetailUI(
        modifier = Modifier,
        uiState =
            DetailState(
                character =
                    Character(
                        id = 1,
                        name = "Jnr",
                        status = "Alive",
                        species = "human",
                        type = "",
                        gender = "male",
                        origin = null,
                        location = null,
                        image = "",
                        episode = listOf(),
                        url = "",
                        created = "",
                    ),
                loading = false,
            ),
    )
}
