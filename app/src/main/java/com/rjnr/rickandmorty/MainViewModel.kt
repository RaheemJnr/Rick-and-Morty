package com.rjnr.rickandmorty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
) : ViewModel() {


    fun start() {
        viewModelScope.launch {

        }
    }

}