package org.challenge.customer.repository.mapper

import org.challenge.customer.domain.Customer
import org.challenge.customer.entity.CustomerEntity
import java.time.LocalDateTime

fun toEntity(customer: Customer): CustomerEntity =
    CustomerEntity(
        id = customer.id,
        name = customer.name,
        email = customer.email,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

fun fromEntity(entity: CustomerEntity?): Customer? {
    return entity?.let {
        Customer(
            id = it.id,
            name = entity.name,
            email = entity.email,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}
