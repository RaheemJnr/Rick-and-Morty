package com.rjnr.screens.ui.domain.mapper

import com.rjnr.networking.model.CharacterDTO
import com.rjnr.screens.ui.domain.Character

fun CharacterDTO.toEntity(): Character {
    return Character(
        id = id ?: 0,
        name = name ?: "",
        status = status ?: "",
        species = species ?: "",
        type = type ?: "",
        gender = gender ?: "",
        origin = origin?.toEntity(),
        location = location?.toEntity(),
        image = image ?: "",
        episode = episode ?: listOf(),
        url = url ?: "",
        created = created ?: "",
    )
}
