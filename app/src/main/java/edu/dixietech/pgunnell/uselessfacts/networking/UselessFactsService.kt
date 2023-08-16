package edu.dixietech.pgunnell.uselessfacts.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import edu.dixietech.pgunnell.uselessfacts.model.Fact
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

object UselessFactsService {

    private const val BASE_URL = "https://uselessfacts.jsph.pl/api/v2"

    private val client
        get() = HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }

    suspend fun getRandomFact(): Fact = client
        .get("$BASE_URL/facts/random")
        .body()
}