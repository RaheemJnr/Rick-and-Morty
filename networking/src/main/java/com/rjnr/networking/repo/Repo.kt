package com.rjnr.networking.repo

import com.rjnr.networking.model.CharacterResponseDTO

interface Repo {
    suspend fun getCharacter(): CharacterResponseDTO
}
