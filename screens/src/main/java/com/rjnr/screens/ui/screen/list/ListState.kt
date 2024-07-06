package com.rjnr.screens.ui.screen.list

import com.rjnr.screens.ui.domain.Character

data class ListState(
    val page: Int,
    val loading: Boolean,
    val character: List<Character>,
)
