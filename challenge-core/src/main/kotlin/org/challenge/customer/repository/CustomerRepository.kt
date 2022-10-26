package org.challenge.customer.repository

import org.challenge.customer.domain.Customer
import java.util.UUID

interface CustomerRepository {

    fun findById(id: UUID): Customer?

    fun findByEmail(email: String): Customer?

    fun save(customer: Customer): Customer?

    fun update(id: UUID, customer: Customer): Customer?

    fun deleteById(id: UUID)
}