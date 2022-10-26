package org.challenge.customer.business.customer

import org.challenge.customer.domain.CustomerCreationEvent
import org.challenge.customer.producer.CustomerProducer
import org.challenge.customer.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

class CustomerProducerService(
    private val customerProducer: CustomerProducer,
    private val customerRepository: CustomerRepository,
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun produce(request: CustomerCreationRequest): Result<Unit> {
        logger.info("publishing a new creation customer event, request={}", request)
        return runCatching {
            val customer = customerRepository.findByEmail(request.email)
            if (customer == null) {
                customerProducer.produce(
                    CustomerCreationEvent(
                        id = UUID.randomUUID(),
                        name = request.name,
                        email = request.email
                    )
                )
            }
        }
    }
}