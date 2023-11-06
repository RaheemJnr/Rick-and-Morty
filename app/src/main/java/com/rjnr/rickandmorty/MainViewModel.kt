package com.rjnr.rickandmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjnr.navigation.ListScreen
import com.rjnr.navigation.Navigation
import kotlinx.coroutines.launch

class MainViewModel(
    private val navigation: Navigation = Navigation()
) : ViewModel() {


    fun start() {
        viewModelScope.launch {
            navigation.navigateTo(ListScreen)
        }
    }

}