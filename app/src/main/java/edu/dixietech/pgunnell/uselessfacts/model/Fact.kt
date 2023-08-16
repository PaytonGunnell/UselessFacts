package edu.dixietech.pgunnell.uselessfacts.model

import edu.dixietech.pgunnell.uselessfacts.utils.URLSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

@Serializable
data class Fact(
    val id: String,
    val text: String,
    val source: String,
    @SerialName("source_url")
    @Serializable(URLSerializer::class)
    val sourceUrl: URL,
    var isFavorited: Boolean = false
//    val language: String,
//    val permalink: String
)
