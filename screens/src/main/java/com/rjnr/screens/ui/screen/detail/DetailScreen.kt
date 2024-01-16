package com.rjnr.screens.ui.screen.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    screen: DetailScreen,
) {
    val viewModel = DetailsViewModel()
    val navigation = navigation()
    onScreenStart {
        viewModel.getCharacterDetails(screen.id)
    }
    val details by viewModel.singleCharacter
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = details.name,
        )
        Text(
            text = "${details.id}",
        )
        Text(
            text = details.gender,
        )
        Text(
            text = details.species,
        )
        Button(
            onClick = {
                viewModel.getCharacterDetails(screen.id)
            },
        ) {
            Text(text = "Hi, this is the details screen")
        }
    }

    BackHandler {
        navigation.onBackPressed()
    }
}
