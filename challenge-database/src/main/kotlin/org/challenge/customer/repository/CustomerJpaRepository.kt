package org.challenge.customer.repository

import org.challenge.customer.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CustomerJpaRepository : JpaRepository<CustomerEntity, UUID> {

    fun findByEmail(email: String): CustomerEntity?
}
