package com.rjnr.screens.ui.screen.listScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun ListScreen(screen: ListScreen) {
    val viewModel: ListViewModel = viewModel()
    val navigation = navigation()
    onScreenStart {
        viewModel.start(screen)
    }
    ListView()
}

// @Composable
// fun List(navigation: Navigation, characters: CharacterResponse = CharacterResponse()) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        LazyColumn() {
//            items(characters.results) {
//                ListView()
//            }
//        }
//    }
// }

@Composable
fun ListView() {
    Row(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            contentDescription = "",
        )

        Column {
            Text(text = "Hiiii")

            Text(text = "Hiiii")
        }
    }
}
