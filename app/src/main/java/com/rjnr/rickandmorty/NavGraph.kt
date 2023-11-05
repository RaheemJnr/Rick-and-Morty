package com.rjnr.rickandmorty

import androidx.compose.runtime.Composable
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Screen
import com.rjnr.screens.ui.screen.DetailScreen
import com.rjnr.screens.ui.screen.ListScreen


@Composable
fun NavGraph(screen: Screen?) {

    when (screen) {
        null -> {

        }

        is ListScreen -> {
            ListScreen(screen = screen)
        }

        is DetailScreen -> {
            DetailScreen(screen = screen)

        }
    }

}