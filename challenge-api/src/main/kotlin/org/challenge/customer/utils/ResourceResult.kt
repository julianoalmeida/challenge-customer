package org.challenge.customer.utils

import org.challenge.customer.domain.BusinessViolation
import java.util.stream.Collectors

fun <U> success(data: U): ResourceResult<U> {
    return ResourceResult.Builder<U>().data(data).success(true).build()
}

fun <U> failure(violations: List<BusinessViolation>): ResourceResult<U> {
    return ResourceResult.Builder<U>()
        .success(false)
        .errors(violations.stream()
            .map(BusinessViolation::name)
            .collect(Collectors.toList())
        )
        .build()
}

class ResourceResult<T> {
    var data: T? = null
    var success: Boolean? = null
    var errors: List<String> = listOf()

    data class Builder<T>(
        var data: T? = null,
        var success: Boolean? = null,
        var errors: List<String> = listOf(),
    ) {
        fun data(data: T) = apply { this.data = data }
        fun success(success: Boolean) = apply { this.success = success }
        fun errors(errors: List<String>) = apply { this.errors = errors }
        fun build(): ResourceResult<T> {
            val resourceResult = ResourceResult<T>()
            resourceResult.data = data
            resourceResult.success = success
            resourceResult.errors = errors
            return resourceResult
        }
    }
}