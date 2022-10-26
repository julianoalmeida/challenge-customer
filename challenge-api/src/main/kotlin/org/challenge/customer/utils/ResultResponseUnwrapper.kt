package org.challenge.customer.utils

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Slf4j
@Component
class ResultResponseUnwrapper {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun <T> unwrap(result: Result<T>): ResponseEntity<ResourceResult<T>> {
        if (result.isSuccess) {
            val resourceResult: ResourceResult<T>? = result.map { success(it) }.getOrNull()
            return ResponseEntity.ok(resourceResult)
        }
        val body = failure<T>(listOf())
        logger.error("result=unexpected_error", result.exceptionOrNull())
        return ResponseEntity.internalServerError().body(body)
    }
}
