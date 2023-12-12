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
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDTO,
    val location: LocationDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

@Serializable
data class LocationDTO(
    val name: String,
    val url: String,
)

