package org.challenge.customer.business.customer

class CustomerChangeRequest(
    override val name: String,
    override val email: String,
) : CustomerRequest