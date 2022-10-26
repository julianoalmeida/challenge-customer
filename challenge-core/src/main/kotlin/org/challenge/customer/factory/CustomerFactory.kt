package org.challenge.customer.factory

import org.challenge.customer.business.customer.CustomerChangeService
import org.challenge.customer.business.customer.CustomerProducerService
import org.challenge.customer.business.customer.CustomerCreationService
import org.challenge.customer.business.customer.CustomerRetrieveService
import org.challenge.customer.facade.CustomerFacade
import org.challenge.customer.producer.CustomerProducer
import org.challenge.customer.repository.CustomerRepository
import org.challenge.customer.repository.TransactionProvider

fun create(
    customerRepository: CustomerRepository,
    transactionProvider: TransactionProvider,
    customerProducer: CustomerProducer
): CustomerFacade {
    val retrieveService = CustomerRetrieveService(customerRepository)
    val creationService = CustomerCreationService(customerRepository, transactionProvider)
    val changeService = CustomerChangeService(customerRepository, transactionProvider)
    val producerService =  CustomerProducerService(customerProducer, customerRepository)
    return CustomerFacade(retrieveService, creationService, producerService, changeService)
}