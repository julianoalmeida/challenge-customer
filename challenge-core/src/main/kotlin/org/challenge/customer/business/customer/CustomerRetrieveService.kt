package org.challenge.customer.business.customer

import org.challenge.customer.domain.Customer
import org.challenge.customer.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

class CustomerRetrieveService(
    private val customerRepository: CustomerRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun retrieveCustomer(id: UUID): Result<Customer?> {
        logger.info("retrieving customer, id={}", id)
        return runCatching { customerRepository.findById(id) }
    }
}

