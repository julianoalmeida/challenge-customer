package org.challenge.customer

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories
@ComponentScan(basePackageClasses = [RepositoryConfig::class])
@EntityScan(basePackages = ["org.challenge.customer.entity"])
class RepositoryConfig