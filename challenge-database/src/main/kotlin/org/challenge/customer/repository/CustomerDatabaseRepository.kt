package org.challenge.customer.repository

import org.challenge.customer.repository.mapper.fromEntity
import org.challenge.customer.repository.mapper.toEntity
import org.challenge.customer.domain.Customer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerDatabaseRepository(
    private val customerJpaRepository: CustomerJpaRepository,
) : CustomerRepository {

    override fun findById(id: UUID): Customer? {
        return fromEntity(customerJpaRepository.findByIdOrNull(id))
    }

    override fun findByEmail(email: String): Customer? {
        return fromEntity(customerJpaRepository.findByEmail(email))
    }

    override fun save(customer: Customer): Customer? {
        val result = customerJpaRepository.save(toEntity(customer))
        return fromEntity(result)
    }

    override fun update(id: UUID, customer: Customer): Customer? {
        var entity = customerJpaRepository.findByIdOrNull(id)
        if (entity != null) {
            entity.email = customer.email
            entity.name = customer.name
            entity.updatedAt = customer.updatedAt
            entity = customerJpaRepository.save(entity)
        }
        return fromEntity(entity)
    }


    override fun deleteById(id: UUID) {
        customerJpaRepository.deleteById(id)
    }
}
