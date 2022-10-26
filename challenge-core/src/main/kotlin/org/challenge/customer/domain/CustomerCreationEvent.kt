package org.challenge.customer.domain

import java.util.UUID

class CustomerCreationEvent(
    val id: UUID,
    val name: String,
    val email: String
)