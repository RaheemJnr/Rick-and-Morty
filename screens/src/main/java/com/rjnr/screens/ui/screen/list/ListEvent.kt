package com.rjnr.screens.ui.screen.list

sealed interface ListEvent {
    data object NextPage : ListEvent

    data class OnChangeItemScrollPosition(val position: Int) : ListEvent
}
