package com.rjnr.screens.ui.screen.composeExt

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.rjnr.navigation.navigation

@SuppressLint("ComposableNaming")
@Composable
fun onScreenStart(
    cleanUp: () -> Unit = {},
    start: () -> Unit,
) {
    val latestStart by rememberUpdatedState(start)
    val latestCleanup by rememberUpdatedState(cleanUp)
    DisposableEffect(navigation().currentScreen) {
        latestStart()
        onDispose { latestCleanup() }
    }
}

class UiWrapperImpl() : UiWrapper()
