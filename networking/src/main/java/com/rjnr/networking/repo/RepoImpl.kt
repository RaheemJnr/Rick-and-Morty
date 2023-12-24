package com.rjnr.networking.repo

import com.rjnr.networking.getAllCharacterRequest
import com.rjnr.networking.getSingleCharacterRequest
import com.rjnr.networking.model.CharacterDTO
import com.rjnr.networking.model.CharacterResponseDTO

class RepoImpl() : Repo {
    override suspend fun getAllCharacters(page: Int): CharacterResponseDTO {
        return getAllCharacterRequest(page = page)
    }

    override suspend fun getSingleCharacter(id: Int): CharacterDTO {
        return getSingleCharacterRequest(id = id)
    }
}
