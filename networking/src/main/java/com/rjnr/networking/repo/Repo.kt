package com.rjnr.networking.repo

import com.rjnr.networking.model.CharacterResponse

interface Repo {
    suspend fun getCharacter(): CharacterResponse
}
