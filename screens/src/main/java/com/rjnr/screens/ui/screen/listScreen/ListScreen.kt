package com.rjnr.screens.ui.screen.listScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = " Hiiiiii")

        Button(
            onClick = {
                navigation.navigateTo(DetailScreen)
            }
        ) {
            Text(text = "Move to next screen")
        }
    }


}