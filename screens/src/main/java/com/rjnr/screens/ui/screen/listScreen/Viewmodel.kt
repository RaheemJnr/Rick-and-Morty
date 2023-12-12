package com.rjnr.screens.ui.screen.listScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjnr.navigation.Navigation
import com.rjnr.networking.repo.Repo
import com.rjnr.networking.repo.RepoImpl
import com.rjnr.screens.ui.domain.CharacterResponse
import com.rjnr.screens.ui.domain.mapper.toEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

data class UIDataState(
    val loading: Boolean = true,
    val character: CharacterResponse? = null,
)

class ListViewModel(
    private val nav: Navigation = Navigation(),
    private val repo: Repo = RepoImpl(),
) : ViewModel() {

    val page = mutableIntStateOf(1)
    private var itemListScrollPosition = 0
    val character: MutableState<List<CharacterResponse>> = mutableStateOf(ArrayList())
    private val _uiState = MutableStateFlow(UIDataState())
    val uiState: StateFlow<UIDataState> = _uiState.asStateFlow()

    init {
        start()
    }

    fun start() {
        viewModelScope.launch {
            try {
                _uiState.update { state ->
                    state.copy(loading = true)
                }
                val result = repo.getAllCharacters(page = page.intValue)
                _uiState.update { characterResponse ->
                    characterResponse.copy(character = result.toEntity(), loading = false)
                }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(loading = false)
                }
                Log.e("emitting failure data", e.toString())
            }
        }

        // nav.onBackPressed[screen]
    }

    fun nextPage() {
        viewModelScope.launch {
            // check if scroll position (20 at the start) + 1  is greater than
            // page * Page size(20 at the start) if true fetch new data
            if ((itemListScrollPosition + 1) >= (page.intValue * PAGE_SIZE)) {
                // add loading to true
                _uiState.update { state ->
                    state.copy(loading = true)
                }
                incrementPage()
                // to show loading
                delay(1000)

                if (page.intValue > 1) {
                    val result = repo.getAllCharacters(page = page.intValue)
                    appendNewItems(result.toEntity())
                }
                _uiState.update { state ->
                    state.copy(loading = false)
                }
            }
        }
    }

    private fun appendNewItems(items: CharacterResponse) {
        val currentList = ArrayList(this.character.value)
        currentList.addAll(listOf(items))
        this.character.value = currentList
    }

    private fun incrementPage() {
        page.intValue = page.intValue + 1
    }

    fun onChangeItemScrollPosition(position: Int) {
        itemListScrollPosition = position
    }
}
