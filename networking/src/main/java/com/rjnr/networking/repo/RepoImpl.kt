package com.rjnr.networking.repo

import com.rjnr.networking.CharacterResponse
import com.rjnr.networking.getCharacterRequest

class RepoImpl() : Repo {
    override suspend fun getCharacter(): CharacterResponse {
        return getCharacterRequest()
    }
}
