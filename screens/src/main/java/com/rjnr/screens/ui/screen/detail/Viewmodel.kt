package com.rjnr.screens.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjnr.navigation.Navigation
import com.rjnr.networking.model.CharacterDTO
import com.rjnr.networking.repo.Repo
import com.rjnr.networking.repo.RepoImpl
import com.rjnr.screens.ui.domain.mapper.toEntity
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

// data class UIDataState(
//    val loading: Boolean = true,
//    val character: List<Character> = ArrayList(),
// )

class DetailsViewModel(
    private val nav: Navigation = Navigation(),
    private val repo: Repo = RepoImpl(),
) : ViewModel() {
    val singleCharacter = mutableStateOf(CharacterDTO().toEntity())
    val loading = mutableStateOf(true)

    init {
        start()
    }

    fun start() {
    }

    fun getCharacterDetails(id: Int) {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = repo.getSingleCharacter(id = id)
                singleCharacter.value = result.toEntity()
                loading.value = false
            } catch (e: Exception) {
                Log.e("emitting failure data", e.toString())
                loading.value = false
            }
        }
    }
}
