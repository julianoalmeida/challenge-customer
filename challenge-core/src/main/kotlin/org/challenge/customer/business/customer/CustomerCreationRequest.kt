package org.challenge.customer.business.customer

class CustomerCreationRequest(
    override val name: String,
    override val email: String,
) : CustomerRequest