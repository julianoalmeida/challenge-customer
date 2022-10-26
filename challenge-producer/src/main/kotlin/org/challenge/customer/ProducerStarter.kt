package org.challenge.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProducerStarter

fun main(args: Array<String>) {
    runApplication<ProducerStarter>(*args)
}