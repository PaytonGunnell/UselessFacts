package edu.dixietech.pgunnell.uselessfacts.utils

import androidx.core.net.toUri
import androidx.room.TypeConverter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URL

object URLSerializer: KSerializer<URL> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("URL", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = URL(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: URL) = encoder.encodeString(value.toString())
}

class URLConverter {
    @TypeConverter
    fun urlFromString(str: String): URL = URL(str)

    @TypeConverter
    fun urlToString(url: URL): String = url.toString()
}