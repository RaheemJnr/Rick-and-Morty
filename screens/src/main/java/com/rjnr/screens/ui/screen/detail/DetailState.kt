package com.rjnr.screens.ui.screen.detail

import com.rjnr.screens.ui.domain.Character

data class DetailState(
    val character: Character,
    val loading: Boolean,
)
