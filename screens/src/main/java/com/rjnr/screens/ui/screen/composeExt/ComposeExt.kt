package com.rjnr.screens.ui.screen.composeExt

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.rjnr.navigation.navigation


@SuppressLint("ComposableNaming")
@Composable
fun onScreenStart(
    cleanUp: () -> Unit = {},
    start: () -> Unit
) {
    DisposableEffect(navigation().currentScreen) {
        start()
        onDispose { cleanUp() }
    }
}


class UiWrapperImpl() : UiWrapper() {

}