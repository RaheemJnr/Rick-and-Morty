package com.rjnr.networking.repo

import com.rjnr.networking.CharacterResponse

interface Repo {
    suspend fun getCharacter(): CharacterResponse
}
