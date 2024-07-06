package com.rjnr.screens.ui.screen.detail

sealed interface DetailEvent {
    data class GetSingle(val id: Int) : DetailEvent
}
