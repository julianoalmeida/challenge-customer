package org.challenge.customer.config

import org.challenge.customer.facade.CustomerFacade
import org.challenge.customer.factory.create
import org.challenge.customer.producer.CustomerProducer
import org.challenge.customer.repository.CustomerRepository
import org.challenge.customer.repository.TransactionProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@ComponentScan(basePackages = ["org.challenge.customer.*"])
@EnableWebMvc
class ConsumerConfiguration(
    private val customerRepository: CustomerRepository,
    private val transactionProvider: TransactionProvider,
    private val customerProducer: CustomerProducer
) {

    @Bean
    fun customerFacade(): CustomerFacade {
        return create(customerRepository, transactionProvider, customerProducer)
    }
}
