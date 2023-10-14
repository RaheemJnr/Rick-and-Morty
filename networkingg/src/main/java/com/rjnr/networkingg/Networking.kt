package com.rjnr.networkingg

const val BASE_URL = "https://rickandmortyapi.com/api"

fun httpClient(): HttpClient =
    HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url(BASE_URL)
        }
    }