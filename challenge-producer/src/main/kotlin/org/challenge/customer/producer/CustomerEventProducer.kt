package org.challenge.customer.producer

import org.challenge.customer.config.QueueDefinition
import org.challenge.customer.domain.CustomerCreationEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class CustomerEventProducer(private val rabbitTemplate: RabbitTemplate) : CustomerProducer {

    override fun produce(event: CustomerCreationEvent) {
        return rabbitTemplate.convertAndSend(
            QueueDefinition.CUSTOMER_CREATION_EXCHANGE,
            QueueDefinition.CUSTOMER_CREATION_ROUTING_KEY,
            event
        )
    }
}