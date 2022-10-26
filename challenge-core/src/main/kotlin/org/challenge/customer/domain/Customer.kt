package org.challenge.customer.domain

import java.time.LocalDateTime
import java.util.UUID

class Customer(
    val id: UUID,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)