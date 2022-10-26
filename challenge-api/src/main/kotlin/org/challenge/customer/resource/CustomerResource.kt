package org.challenge.customer.resource

import org.challenge.customer.facade.CustomerFacade
import org.challenge.customer.domain.Customer
import lombok.extern.slf4j.Slf4j
import org.challenge.customer.business.customer.CustomerCreationRequest
import org.challenge.customer.business.customer.CustomerChangeRequest
import org.challenge.customer.utils.ResourceResult
import org.challenge.customer.utils.ResultResponseUnwrapper
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Slf4j
@RestController
@RequestMapping(
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class CustomerResource(
    private val customerFacade: CustomerFacade,
    private val resultResponseUnwrapper: ResultResponseUnwrapper,
) {

    @GetMapping("/customer/{id}")
    fun retrieveById(
        @PathVariable("id") id: UUID,
    ): ResponseEntity<ResourceResult<Customer?>> {
        return resultResponseUnwrapper.unwrap(customerFacade.retrieveCustomer(id))
    }

    @PostMapping("/customer")
    fun create(@RequestBody request: CustomerRequest): ResponseEntity<ResourceResult<Unit>> {
        return resultResponseUnwrapper.unwrap(
            customerFacade.produceCustomerCreationEvent(
                CustomerCreationRequest(
                    name = request.name,
                    email = request.email
                )
            )
        )
    }

    @PutMapping("/customer/{id}")
    fun update(
        @PathVariable("id") id: UUID,
        @RequestBody request: CustomerRestRequest,
    ): ResponseEntity<ResourceResult<Customer?>> {
        return resultResponseUnwrapper.unwrap(
            customerFacade.updateCustomer(
                id,
                CustomerChangeRequest(
                    name = request.name,
                    email = request.email
                )
            )
        )
    }
}