package org.challenge.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumerStarter

fun main(args: Array<String>) {
    runApplication<ConsumerStarter>(*args)
}