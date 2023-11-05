package com.rjnr.rickandmorty

import androidx.compose.runtime.Composable
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Screen


@Composable
fun NavGraph(screen: Screen?) {

    when (screen) {
        null -> {

        }

        is ListScreen -> {
        }

        is DetailScreen -> {

        }
    }

}