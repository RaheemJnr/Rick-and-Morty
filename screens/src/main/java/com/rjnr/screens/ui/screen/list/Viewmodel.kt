package com.rjnr.screens.ui.screen.list

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.rjnr.navigation.Navigation
import com.rjnr.networking.repo.Repo
import com.rjnr.networking.repo.RepoImpl
import com.rjnr.screens.ui.domain.Character
import com.rjnr.screens.ui.domain.mapper.toEntity
import com.rjnr.screens.ui.screen.viewModel.ComposeViewModel
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

class ListViewModel(
    private val nav: Navigation = Navigation(),
    private val repo: Repo = RepoImpl(),
) : ComposeViewModel<ListState, ListEvent>() {
    private val page = mutableIntStateOf(0)
    private val character: MutableState<List<Character>> = mutableStateOf(ArrayList())
    private val loading = mutableStateOf(true)
    private var itemListScrollPosition = 0

    @Composable
    override fun uiState(): ListState {
        return ListState(
            page = page.intValue,
            character = character.value,
            loading = loading.value,
        )
    }

    init {
        start()
    }

    override fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.NextPage -> nextPage()
            is ListEvent.OnChangeItemScrollPosition -> onChangeItemScrollPosition(event.position)
        }
    }

    fun start() {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = repo.getAllCharacters(page = 1)
                character.value = result.toEntity().results
                loading.value = false
            } catch (e: Exception) {
                Log.e("emitting failure data", e.toString())
                loading.value = false
            }
        }
    }

    private fun nextPage() {
        viewModelScope.launch {
            // check if scroll position (20 at the start) + 1  is greater than
            // page * Page size(20 at the start) if true fetch new data
            if (itemListScrollPosition + 1 >= (page.intValue * PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                if (page.intValue > 1) {
                    try {
                        val result = repo.getAllCharacters(page = page.intValue)
                        appendNewItems(result.toEntity().results)
                    } catch (e: Exception) {
                        loading.value = false
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
        page.intValue += 1
    }

    fun onChangeItemScrollPosition(position: Int) {
        itemListScrollPosition = position
    }
}
