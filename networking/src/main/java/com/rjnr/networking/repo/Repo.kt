package com.rjnr.networking.repo

import com.rjnr.networking.model.CharacterDTO
import com.rjnr.networking.model.CharacterResponseDTO

interface Repo {
    suspend fun getAllCharacters(page: Int): CharacterResponseDTO

    suspend fun getSingleCharacter(id: Int): CharacterDTO
}
