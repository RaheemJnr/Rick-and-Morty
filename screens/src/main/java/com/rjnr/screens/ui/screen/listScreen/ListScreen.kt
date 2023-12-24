package com.rjnr.screens.ui.screen.listScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun ListScreen(screen: ListScreen) {
    val viewModel: ListViewModel = viewModel()
    val page = viewModel.page.intValue
    val loading = viewModel.loading.value
    val character = viewModel.character.value

    val nav = navigation()
    onScreenStart {
        viewModel.start()
    }
    List(
        navigation = nav,
        viewModel = viewModel,
        page = page,
        loading = loading,
        character = character,
    )
}

@Composable
fun List(
    navigation: Navigation,
    viewModel: ListViewModel,
    page: Int,
    loading: Boolean,
    character: List<Character>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn() {
            if (loading && character.isEmpty()) {
                item {
                    Loading()
                }
            } else {
                itemsIndexed(character) { index, item ->
                    viewModel.onChangeItemScrollPosition(index)
                    Log.i("emitting data", "index:$index")

                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        viewModel.nextPage()
                        Log.i("item size", "next page size:${character.size}")
                    }
                    ListView(uiState = item)
                }
            }
        }
    }
}

@Composable
fun Loading() {
    CircularProgressIndicator()
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
