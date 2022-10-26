package org.challenge.customer.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.charset.Charset

@Component
class JsonUtils(private val mapper: ObjectMapper) {

    fun toJson(`object`: Any?): String {
        return try {
            mapper.writeValueAsString(`object`)
        } catch (exception: JsonProcessingException) {
            throw exception
        }
    }

    fun <T> fromJson(json: String?, clazz: Class<T>?): T {
        return try {
            mapper.readValue(json, clazz)
        } catch (exception: IOException) {
            throw exception
        }
    }

    fun <T> fromJson(message: ByteArray?, clazz: Class<T>?): T {
        val json = String(message!!, UTF8)
        return fromJson(json, clazz)
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }
}