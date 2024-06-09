package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
    private val _character = mutableStateOf(CharacterDTO())
    val character: State<CharacterDTO> = _character
    private val _loading = mutableStateOf(true)
    val loading: State<Boolean> = _loading

    @Composable
    override fun uiState(): DetailState {
        Log.i("VM list", "${character.value.toEntity()}")
        return DetailState(
            character = character.value.toEntity(),
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
        _loading.value = true
        viewModelScope.launch {
            try {
                val result = repo.getSingleCharacter(id = id)
                // singleCharacter.value = result.toEntity()
                _character.value = result
                _loading.value = false
                Log.i("VM list", "${character.value}")
            } catch (e: Exception) {
                Log.e("emitting failure data", e.toString())
                _loading.value = false
            }
        }
    }
}
