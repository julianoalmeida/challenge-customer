package org.challenge.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiStarter

fun main(args: Array<String>) {
    runApplication<ApiStarter>(*args)
}