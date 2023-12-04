package com.rjnr.screens.ui.screen.composeExt

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity

// Interface representing a screen wrapper
interface IScreenWrapper {
    fun uiWrapper(): UiWrapper
}

// Abstract class implementing IScreenWrapper
abstract class ScreenWrapper : IScreenWrapper

// Abstract class representing a UI wrapper
abstract class UiWrapper {
    // Nullable properties for screen dimensions, with null checks in the getters
    var screenWidth: Int? = null
        get() = field ?: throw IllegalStateException("screenWidth not initialized")

    var screenHeight: Int? = null
        get() = field ?: throw IllegalStateException("screenHeight not initialized")
}

// CompositionLocal key for accessing UiWrapper in Compose
val LocalRMContext = compositionLocalOf<UiWrapper> { error("No LocalIvyContext") }

// Composable function for wrapping UI with screen dimensions and a surface
@Composable
fun RMUIWrapper(
    screenWrapper: IScreenWrapper,
    includeSurface: Boolean = true,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    // Get the UiWrapper from the IScreenWrapper
    val wrapper = screenWrapper.uiWrapper()

    // Provide the UiWrapper through CompositionLocal
    CompositionLocalProvider(LocalRMContext provides wrapper) {
        // Wrap with surface if specified
        WrapWithSurface {
            // BoxWithConstraints to get the screen dimensions
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                // Set screen dimensions in UiWrapper based on the constraints
                wrapper.screenWidth = with(LocalDensity.current) {
                    maxWidth.roundToPx()
                }
                wrapper.screenHeight = with(LocalDensity.current) {
                    maxHeight.roundToPx()
                }

                // Call the content lambda with the screen dimensions
                content()
            }
        }
    }
}


@Composable
private fun WrapWithSurface(
    content: @Composable () -> Unit,
) {
    Surface {
        content()
    }
}