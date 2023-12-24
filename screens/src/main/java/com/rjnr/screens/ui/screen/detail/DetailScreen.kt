package com.rjnr.screens.ui.screen.detail

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.screen.composeExt.onScreenStart
import com.rjnr.screens.ui.screen.viewModel.BaseViewModel

@Composable
fun DetailScreen(modifier: Modifier = Modifier, screen: DetailScreen) {
    val viewModel: BaseViewModel = viewModel()
    Log.i("detailScreen", "screen number: ${screen.id}")
    val navigation = navigation()
    onScreenStart {
        viewModel.getCharacterDetails(screen.id)
    }

    BackHandler {
        navigation.onBackPressed()
    }

    val details by viewModel.singleCharacter
    Log.i("detailScreen", "screen number: $details")

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
}
