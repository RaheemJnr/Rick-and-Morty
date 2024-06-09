package com.rjnr.rickandmorty

import androidx.compose.runtime.Composable
import com.rjnr.navigation.*
import com.rjnr.screens.ui.screen.detail.DetailScreen
import com.rjnr.screens.ui.screen.list.ListScreen
import com.rjnr.screens.ui.screen.onboarding.OnboardingScreen

@Composable
fun NavGraph(screen: Screen?) {
    when (screen) {
        null -> {
        }
        is Screens.OnboardingScreen -> {
            OnboardingScreen(screen = screen)
        }

        is Screens.ListScreen -> {
            ListScreen(screen = screen)
        }

        is Screens.DetailScreen -> {
            DetailScreen(screen = screen)
        }
    }
}
