package com.rjnr.screens.ui.screen.listScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjnr.navigation.Navigation
import com.rjnr.networking.CharacterResponse
import com.rjnr.networking.repo.Repo
import com.rjnr.networking.repo.RepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UIDataState(
    val character: CharacterResponse? = null,
)

class ListViewModel(
    private val nav: Navigation = Navigation(),
    private val repo: Repo = RepoImpl(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIDataState())
    val uiState: StateFlow<UIDataState> = _uiState.asStateFlow()

    fun start() {
        viewModelScope.launch {
            val result = repo.getCharacter()
            _uiState.update { characterResponse ->
                characterResponse.copy(character = result)
            }
        }
        // nav.onBackPressed[screen]
    }

    init {
        start()
    }
}
