package com.rjnr.networking.repo

import com.rjnr.networking.getCharacterRequest
import com.rjnr.networking.model.CharacterResponseDTO

class RepoImpl() : Repo {
    override suspend fun getCharacter(): CharacterResponseDTO {
        return getCharacterRequest()
    }
}
