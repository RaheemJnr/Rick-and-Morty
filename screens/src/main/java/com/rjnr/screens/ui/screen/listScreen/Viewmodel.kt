package com.rjnr.screens.ui.screen.listScreen

import androidx.lifecycle.ViewModel
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation

class ListViewModel(private val nav: Navigation = Navigation()) : ViewModel() {

    fun start(screen: ListScreen) {
        nav.onBackPressed[screen]
    }
}
