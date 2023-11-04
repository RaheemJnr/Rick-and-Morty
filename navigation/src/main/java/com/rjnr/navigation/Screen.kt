package com.rjnr.navigation

sealed interface Screen {

    val isLegacy: Boolean
        get() = false
}