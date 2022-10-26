package org.challenge.customer.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
@ComponentScan(basePackageClasses = [RabbitConfig::class])
class RabbitConfig {

    private val username: String = "admin"

    private val password: String = "admin"

    private val host: String = "localhost"

    @Bean
    fun queue(): Queue {
        return Queue(QueueDefinition.CUSTOMER_CREATION_QUEUE, true)
    }

    @Bean
    fun dlq(): Queue {
        return Queue(QueueDefinition.CUSTOMER_CREATION_DLQ_QUEUE, true)
    }

    @Bean
    fun exchange(): Exchange {
        return ExchangeBuilder.directExchange(QueueDefinition.CUSTOMER_CREATION_EXCHANGE).durable(true).build()
    }

    @Bean
    fun dlqExchange(): Exchange {
        return ExchangeBuilder.directExchange(QueueDefinition.CUSTOMER_CREATION_DLQ_EXCHANGE).durable(true).build()
    }

    @Bean
    fun bindingQueue(): Binding {
        return BindingBuilder.bind(queue()).to(exchange()).with(QueueDefinition.CUSTOMER_CREATION_ROUTING_KEY).noargs()
    }

    @Bean
    fun bindingDlq(): Binding {
        return BindingBuilder.bind(dlq()).to(dlqExchange()).with(QueueDefinition.CUSTOMER_CREATION_DLQ_ROUTING_KEY)
            .noargs()
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val cachingConnectionFactory = CachingConnectionFactory(host)
        cachingConnectionFactory.username = username
        cachingConnectionFactory.setPassword(password)
        return cachingConnectionFactory
    }

    @Bean
    fun jsonMessageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory?): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory())
        rabbitTemplate.messageConverter = jsonMessageConverter()
        return rabbitTemplate
    }
}