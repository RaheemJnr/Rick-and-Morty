package com.rjnr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner


/**
 * The CompositionLocal containing the current [ViewModelStoreOwner].
 */
object LocalViewModelStoreOwner {
    private val LocalViewModelStoreOwner =
        compositionLocalOf<ViewModelStoreOwner?> { null }

    /**
     * Returns current composition local value for the owner or `null` if one has not
     * been provided nor is one available via [findViewTreeViewModelStoreOwner] on the
     * current [LocalView].
     */
    public val current: ViewModelStoreOwner?
        @Composable
        get() = LocalViewModelStoreOwner.current
            ?: LocalView.current.findViewTreeViewModelStoreOwner()

    /**
     * Associates a [LocalViewModelStoreOwner] key to a value in a call to
     * [CompositionLocalProvider].
     */
    public infix fun provides(viewModelStoreOwner: ViewModelStoreOwner):
            ProvidedValue<ViewModelStoreOwner?> {
        return LocalViewModelStoreOwner.provides(viewModelStoreOwner)
    }
}