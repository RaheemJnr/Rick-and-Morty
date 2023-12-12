package com.rjnr.screens.ui.domain.mapper

import com.rjnr.networking.model.CharacterResponseDTO
import com.rjnr.screens.ui.domain.CharacterResponse

fun CharacterResponseDTO.toEntity(): CharacterResponse {
    return CharacterResponse(
        info = info.toEntity(),
        results = results.map { it.toEntity() },
    )
}
