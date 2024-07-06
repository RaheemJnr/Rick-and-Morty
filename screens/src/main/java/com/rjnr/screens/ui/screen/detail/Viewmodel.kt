package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.rjnr.networking.model.CharacterDTO
import com.rjnr.networking.repo.Repo
import com.rjnr.networking.repo.RepoImpl
import com.rjnr.screens.ui.domain.mapper.toEntity
import com.rjnr.screens.ui.screen.viewModel.ComposeViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repo: Repo = RepoImpl(),
) : ComposeViewModel<DetailState, DetailEvent>() {
    private val character = mutableStateOf(CharacterDTO())
    private val loading = mutableStateOf(true)

    @Composable
    override fun uiState(): DetailState {
        return DetailState(
            character = character.value.toEntity(),
            loading = loading.value,
        )
    }

    override fun onEvent(event: DetailEvent) {
//        when (event) {
//            is DetailEvent.GetSingle -> getCharacterDetails(event.id)
//        }
    }

    fun start(id: Int) {
        getCharacterDetails(id = id)
    }

    private fun getCharacterDetails(id: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                val result = repo.getSingleCharacter(id = id)
                character.value = result
                loading.value = false
            } catch (e: Exception) {
                Log.e("emitting failure data", e.toString())
                loading.value = false
            }
        }
    }
}
