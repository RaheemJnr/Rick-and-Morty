package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjnr.networking.model.CharacterDTO
import com.rjnr.networking.repo.Repo
import com.rjnr.networking.repo.RepoImpl
import com.rjnr.screens.ui.domain.mapper.toEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repo: Repo = RepoImpl(),
) : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDTO())
    val uiState: StateFlow<CharacterDTO> = _uiState.asStateFlow()

    // val singleCharacter: MutableState<Character> = mutableStateOf(CharacterDTO().toEntity())
    val loading = mutableStateOf(true)

    init {
        // start()
    }

    fun start(id: Int) {
        getCharacterDetails(id = id)
    }

    private fun getCharacterDetails(id: Int) {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = repo.getSingleCharacter(id = id)
                // singleCharacter.value = result.toEntity()
                _uiState.value = result
                loading.value = false
                Log.i("VMlist1", "$result")
                Log.i("VMlist2", "${result.toEntity()}")
            } catch (e: Exception) {
                Log.e("emitting failure data", e.toString())
                loading.value = false
            }
        }
    }
}
