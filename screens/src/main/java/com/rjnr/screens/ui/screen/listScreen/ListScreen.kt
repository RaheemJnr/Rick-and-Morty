package com.rjnr.screens.ui.screen.listScreen

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rjnr.navigation.ListScreen
import com.rjnr.screens.ui.screen.composeExt.onScreenStart

@Composable
fun ListScreen(screen: ListScreen) {
    val viewModel: ListViewModel = viewModel()

    onScreenStart {
        viewModel.start(screen)
    }

    UI()
}

@Composable
fun UI() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = " Hiiiiii")
    }


}