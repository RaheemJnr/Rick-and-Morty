package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.navigation

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    screen: DetailScreen,
    viewmodel: DetailsViewModel = viewModel(),
) {
    val details = viewmodel.uiState()
    LaunchedEffect(screen.id) {
        viewmodel.start(screen.id)
    }
    val navigation = navigation()
//    onScreenStart(cleanUp = {}) {
//        viewmodel.start(screen.id)
//    }

    DetailUI(modifier, details)

    BackHandler {
        navigation.onBackPressed()
    }
    Log.i("Ui details", "${details.character}")
}

@Composable
private fun DetailUI(
    modifier: Modifier,
    details: DetailState,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = details.character.name,
        )
        Log.i("listt", "${details.character}")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPrev() {
//    DetailUI(
//        modifier = Modifier,
//        details =
//            Character(
//                id = 1,
//                name = "Jnr",
//                status = "Alive",
//                species = "human",
//                type = "",
//                gender = "male",
//                origin = null,
//                location = null,
//                image = "",
//                episode = listOf(),
//                url = "",
//                created = "",
//            ),
//    )
}
