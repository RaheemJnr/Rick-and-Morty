package com.rjnr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf


private val localNavigation = compositionLocalOf<Navigation> { error("No Local Navigation") }

@Composable
fun NavigationRoot(
    navigation: Navigation,
    navGraph: @Composable (screen: Screen?) -> Unit
) {
    CompositionLocalProvider(
        localNavigation provides navigation,
    ) {
        val viewModelStore = LocalViewModelStoreOwner.current
        DisposableEffect(navigation.currentScreen) {
            onDispose {
                // Destroy viewModels only for non-legacy screens
                if (navigation.lastScreen?.isLegacy == false) {
                    viewModelStore?.viewModelStore?.clear()
                }
            }
        }
        navGraph(navigation.currentScreen)
    }
}


@Composable
fun navigation(): Navigation {
    return localNavigation.current
}