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
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.domain.mapper.toEntity
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

// data class UIDataState(
//    val loading: Boolean = true,
//    val character: List<Character> = ArrayList(),
// )

class ListViewModel(
    private val nav: Navigation = Navigation(),
    private val repo: Repo = RepoImpl(),
) : ViewModel() {

    val page = mutableIntStateOf(0)
    val character: MutableState<List<Character>> = mutableStateOf(ArrayList())
    val loading = mutableStateOf(true)
    private var itemListScrollPosition = 0
//    private val _uiState = MutableStateFlow(UIDataState())
//    val uiState: StateFlow<UIDataState> = _uiState.asStateFlow()

    init {
        start()
    }

    fun start() {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = repo.getAllCharacters(page = 1)
                Log.i("first page number", "pageNumber:${page.intValue}")
                character.value = result.toEntity().results
                Log.i("emitting data", ":${result.toEntity().info}")
                loading.value = false
            } catch (e: Exception) {
                Log.e("emitting failure data", e.toString())
            }
        }

        // nav.onBackPressed[screen]
    }

    fun nextPage() {
        viewModelScope.launch {
            // check if scroll position (20 at the start) + 1  is greater than
            // page * Page size(20 at the start) if true fetch new data
            if (itemListScrollPosition + 1 >= (page.intValue * PAGE_SIZE)) {
                Log.i("scroll position1", "position:$itemListScrollPosition")
                // add loading to true
                loading.value = true
                incrementPage()
                Log.i("emitting data", "pageNumber:${page.intValue}")

                if (page.intValue > 1) {
                    try {
                        val result = repo.getAllCharacters(page = page.intValue)
                        Log.i("emitting data", "${page.intValue}")
                        appendNewItems(result.toEntity().results)
                        Log.i("emitting data", "${result.toEntity()}")
                    } catch (e: Exception) {
                        Log.e("emitting failure data", e.toString())
                    }
                }
                loading.value = false
            }
        }
    }

    private fun appendNewItems(items: List<Character>) {
        val currentList = ArrayList(this.character.value)
        currentList.addAll(items)
        this.character.value = currentList
    }

    private fun incrementPage() {
        page.intValue = page.intValue + 1
    }

    fun onChangeItemScrollPosition(position: Int) {
        itemListScrollPosition = position
        Log.i("scroll position", "position:$position")
    }
}
