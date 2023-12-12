package com.rjnr.screens.ui.screen.listScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.navigation
import com.rjnr.networking.model.Character
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun ListScreen(screen: ListScreen) {
    val viewModel: ListViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val nav = navigation()
    onScreenStart {
        viewModel.start()
    }
    List(navigation = nav, state = uiState)
}

@Composable
fun List(navigation: Navigation, state: UIDataState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn() {
            state.character?.let {
                itemsIndexed(it.results) { _, item ->
                    ListView(uiState = item)
                }
            }
        }
    }
}

@Composable
fun ListView(uiState: Character) {
    Row(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = uiState.image,
            contentDescription = "",
        )

        Column {
            Text(text = uiState.name)

            Text(text = uiState.gender)
        }
    }
}
