package edu.dixietech.pgunnell.uselessfacts.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import edu.dixietech.pgunnell.uselessfacts.utils.URLSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

@Serializable
@Entity("fact_table")
data class Fact(
    @PrimaryKey val id: String,
    val text: String,
    val source: String,
    @SerialName("source_url")
    @Serializable(URLSerializer::class)
    val sourceUrl: URL,
    var isFavorited: Boolean = false
//    val language: String,
//    val permalink: String
)

