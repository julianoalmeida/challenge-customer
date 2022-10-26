package org.challenge.customer.business.customer

import org.challenge.customer.domain.Customer
import org.challenge.customer.repository.CustomerRepository
import org.challenge.customer.repository.TransactionProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.UUID

class CustomerChangeService(
    private val customerRepository: CustomerRepository,
    private val transactionProvider: TransactionProvider
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun changeCustomer(id: UUID, request: CustomerChangeRequest): Result<Customer?> {
        logger.info("changing a customer, request={}", request)
        return runCatching {
            customerRepository.update(
                id,
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