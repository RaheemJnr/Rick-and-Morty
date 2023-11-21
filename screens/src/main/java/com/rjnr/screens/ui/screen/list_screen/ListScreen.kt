package com.rjnr.screens.ui.screen.list_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import com.rjnr.navigation.navigation
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun ListScreen(screen: ListScreen) {
    val viewModel: ListViewModel = viewModel()
    val navigation = navigation()
    onScreenStart {
        viewModel.start(screen)
    }

    UI(navigation)
}

@Composable
fun UI(navigation: Navigation) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = " Hiiiiii List Screen",
            style = MaterialTheme.typography.headlineLarge
        )

        Button(
            onClick = {
                navigation.navigateTo(DetailScreen)
            }
        ) {

            Text(
                text = "Move to next screen",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }


}