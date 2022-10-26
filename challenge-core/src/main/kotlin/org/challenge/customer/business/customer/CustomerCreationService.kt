package org.challenge.customer.business.customer

import org.challenge.customer.domain.Customer
import org.challenge.customer.repository.CustomerRepository
import org.challenge.customer.repository.TransactionProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.UUID

class CustomerCreationService(
    private val customerRepository: CustomerRepository,
    private val transactionProvider: TransactionProvider
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun createCustomer(request: CustomerCreationRequest): Result<Customer?> {
        logger.info("creating a new customer, request={}", request)
        return runCatching {
            customerRepository.save(
                Customer(
                    id = UUID.randomUUID(),
                    name = request.name,
                    email = request.email,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            )
        }
    }
}