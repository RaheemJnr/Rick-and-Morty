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


interface IScreenWrapper {
    fun uiWrapper(): UiWrapper
}

abstract class ScreenWrapper() : IScreenWrapper {

}

abstract class UiWrapper {

    var screenWidth: Int = -1
        get() {
            return if (field > 0) field else throw IllegalStateException("screenWidth not initialized")
        }

    var screenHeight: Int = -1
        get() {
            return if (field > 0) field else throw IllegalStateException("screenHeight not initialized")
        }

}


val LocalRMContext = compositionLocalOf<UiWrapper> { error("No LocalIvyContext") }

@Composable
fun RMUIWrapper(
    screenWrapper: IScreenWrapper,
    includeSurface: Boolean = true,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    val wrapper = screenWrapper.uiWrapper()

    CompositionLocalProvider(
        LocalRMContext provides wrapper,
    ) {

        WrapWithSurface(includeSurface = includeSurface) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                wrapper.screenWidth = with(LocalDensity.current) {
                    maxWidth.roundToPx()
                }
                wrapper.screenHeight = with(LocalDensity.current) {
                    maxHeight.roundToPx()
                }

                content()
            }
        }

    }
}

@Composable
private fun WrapWithSurface(
    includeSurface: Boolean,
    content: @Composable () -> Unit,
) {
    if (includeSurface) {
        Surface {
            content()
        }
    } else {
        content()
    }
}
