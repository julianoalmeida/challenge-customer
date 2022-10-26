package org.challenge.customer.consumer

import org.challenge.customer.business.customer.CustomerCreationRequest
import org.challenge.customer.domain.CustomerCreationEvent
import org.challenge.customer.facade.CustomerFacade
import org.challenge.customer.utils.JsonUtils
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.stereotype.Service

@Service
class CustomerCreationConsumer(
    private val customerFacade: CustomerFacade,
    private val jsonUtils: JsonUtils,
) : MessageListener {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onMessage(message: Message?) {
        logger.info("receive message from ${message?.messageProperties?.consumerQueue}")
        message?.let {
            val customer = jsonUtils.fromJson(message.body, CustomerCreationEvent::class.java)
            customerFacade.createCustomer(
                CustomerCreationRequest(
                    name = customer.name,
                    email = customer.email
                )
            )
            logger.info("customer $customer")
        } ?: logger.warn("No message found")
    }
}
