package com.rjnr.networking.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponseDTO(
    val info: InfoDTO,
    val results: List<CharacterDTO>,
)

@Serializable
data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)

@Serializable
data class CharacterDTO(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: LocationDTO? = null,
    val location: LocationDTO? = null,
    val image: String? = null,
    val episode: List<String>? = listOf(),
    val url: String? = null,
    val created: String? = null,
)

@Serializable
data class LocationDTO(
    val name: String? = null,
    val url: String? = null,
)
