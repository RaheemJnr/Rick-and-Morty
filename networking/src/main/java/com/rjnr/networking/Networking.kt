package com.rjnr.networking

import com.rjnr.networking.model.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val BASE_URL = "https://rickandmortyapi.com/api/"

internal fun httpClient(): HttpClient =
    HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }
        defaultRequest {
            url(BASE_URL)
        }
    }

suspend fun getCharacterRequest(): CharacterResponse {
    return httpClient().get("character").body()
}
