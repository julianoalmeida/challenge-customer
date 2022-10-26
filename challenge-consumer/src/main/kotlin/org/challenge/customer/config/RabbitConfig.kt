package org.challenge.customer.config

import org.aopalliance.aop.Advice
import org.challenge.customer.consumer.CustomerCreationConsumer
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.MessageListenerContainer
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig(
    private val connectionFactory: ConnectionFactory,
    private val customerCreationConsumer: CustomerCreationConsumer,
    private val simpleRabbitListenerContainerFactory: SimpleRabbitListenerContainerFactory,
) {

    @Bean
    fun listenerContainer(): MessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(QueueDefinition.CUSTOMER_CREATION_QUEUE)
        container.setMessageListener(customerCreationConsumer)
        simpleRabbitListenerContainerFactory.adviceChain?.let {
            container.setAdviceChain(*it, retryPolicy())
        }
        return container
    }

    private fun retryPolicy(): Advice {
        return RetryInterceptorBuilder
            .stateless()
            .maxAttempts(5)
            .backOffOptions(
                1000, // Initial interval
                2.0, // Multiplier
                6000 // Max interval
            )
            .recoverer(RejectAndDontRequeueRecoverer())
            .build()
    }
}