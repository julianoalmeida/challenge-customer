package org.challenge.customer.producer

import org.challenge.customer.domain.CustomerCreationEvent

interface CustomerProducer {
    fun produce(event: CustomerCreationEvent)
}