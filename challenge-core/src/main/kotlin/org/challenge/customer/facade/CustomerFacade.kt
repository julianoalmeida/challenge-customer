package org.challenge.customer.facade

import org.challenge.customer.business.customer.CustomerChangeRequest
import org.challenge.customer.business.customer.CustomerChangeService
import org.challenge.customer.business.customer.CustomerProducerService
import org.challenge.customer.business.customer.CustomerCreationRequest
import org.challenge.customer.business.customer.CustomerCreationService
import org.challenge.customer.business.customer.CustomerRetrieveService
import org.challenge.customer.domain.Customer
import java.util.UUID

class CustomerFacade(
    private val customerRetrieveService: CustomerRetrieveService,
    private val customerCreationService: CustomerCreationService,
    private val customerProducerService: CustomerProducerService,
    private val customerChangeService: CustomerChangeService
) {

    fun retrieveCustomer(id: UUID): Result<Customer?> {
        return customerRetrieveService.retrieveCustomer(id)
    }

    fun produceCustomerCreationEvent(request: CustomerCreationRequest): Result<Unit> {
        return customerProducerService.produce(request)
    }

    fun createCustomer(request: CustomerCreationRequest): Result<Customer?> {
        return customerCreationService.createCustomer(request)
    }

    fun updateCustomer(id: UUID, request: CustomerChangeRequest): Result<Customer?> {
        return customerChangeService.changeCustomer(id, request)
    }
}