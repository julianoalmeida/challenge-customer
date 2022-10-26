package org.challenge.customer.config

object QueueDefinition {
    const val CUSTOMER_CREATION_QUEUE = "customer.creation.queue"
    const val CUSTOMER_CREATION_EXCHANGE = "customer.creation.exchange"
    const val CUSTOMER_CREATION_ROUTING_KEY = "customer.creation.routingKey"
    const val CUSTOMER_CREATION_DLQ_EXCHANGE = "customer.creation.dlq.exchange"
    const val CUSTOMER_CREATION_DLQ_QUEUE = "customer.creation.dlq.queue"
    const val CUSTOMER_CREATION_DLQ_ROUTING_KEY = "customer.creation.dlq.routingKey"
}