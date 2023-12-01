package com.rjnr.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Stack


@Stable
class Navigation() {
    var currentScreen: Screen? by mutableStateOf(null)
        private set


    val onBackPressed: MutableMap<Screen, () -> Boolean> = mutableMapOf()

    private val backStack: Stack<Screen> = Stack()
    var lastScreen: Screen? = null
        private set

    fun navigateTo(screen: Screen) {
        if (lastScreen != null) {
            backStack.push(lastScreen)
        }
        switchScreen(screen)
    }

    private fun backStackEmpty() = backStack.empty()

    private fun popBackStack() {
        backStack.pop()
    }


    fun onBackPressed(): Boolean {

        val specialHandling = onBackPressed.getOrDefault(currentScreen) { false }.invoke()
        return specialHandling || back()
    }

    fun back(): Boolean {
        if (!backStack.empty()) {
            switchScreen(backStack.pop())
            return true
        }
        return false
    }

    private fun switchScreen(screen: Screen) {
        this.currentScreen = screen
        lastScreen = screen
    }

    fun resetBackStack() {
        while (!backStackEmpty()) {
            popBackStack()
        }
        lastScreen = null
    }
}