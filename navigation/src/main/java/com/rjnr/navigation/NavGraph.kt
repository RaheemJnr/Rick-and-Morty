package com.rjnr.navigation

import androidx.compose.runtime.Composable
import com.rjnr.screens.ui.screen.DetailScreen
import com.rjnr.screens.ui.screen.ListScreen

@Composable
fun NavGraph(screen: Screen?) {

    when (screen) {
        null -> {

        }

        is ListScreen -> {
            ListScreen()
        }

        is DetailScreen -> {
            DetailScreen()

        }
    }

}