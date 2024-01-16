package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Suppress("ktlint:standard:function-naming")
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    screen: DetailScreen,
) {
    val viewModel = DetailsViewModel()
    val details by viewModel.uiState.collectAsState()
    val navigation = navigation()
    onScreenStart {
        viewModel.getCharacterDetails(screen.id)
    }

    DetailUI(modifier, details)

    BackHandler {
        navigation.onBackPressed()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun DetailUI(
    modifier: Modifier,
    details: Character,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = details.name,
        )
        Log.i("Details fetched list", "$details")

    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun DetailScreenPrev() {
    DetailUI(
        modifier = Modifier,
        details =
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
    )
}
