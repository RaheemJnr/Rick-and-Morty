package com.rjnr.rickandmorty

import androidx.compose.runtime.Composable
import com.rjnr.navigation.DetailScreen
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.OnboardingScreen
import com.rjnr.navigation.Screen
<<<<<<< HEAD
import com.rjnr.screens.ui.screen.detail.DetailScreen
import com.rjnr.screens.ui.screen.list.ListScreen
import com.rjnr.screens.ui.screen.onboarding.OnboardingScreen
=======
import com.rjnr.screens.ui.screen.detailScreen.DetailScreen
import com.rjnr.screens.ui.screen.listScreen.ListScreen
>>>>>>> master


@Composable
fun NavGraph(screen: Screen?) {

    when (screen) {
        null -> {

        }
        is OnboardingScreen -> {
            OnboardingScreen(screen = screen)
        }

        is ListScreen -> {
            ListScreen(screen = screen)
        }

        is DetailScreen -> {

            DetailScreen(screen = screen)

        }


    }

}