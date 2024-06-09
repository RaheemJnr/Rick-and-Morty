package com.rjnr.navigation

sealed class Screens {
    data object ListScreen : Screen

    data object OnboardingScreen : Screen

    data class DetailScreen(val id: Int) : Screen
}
