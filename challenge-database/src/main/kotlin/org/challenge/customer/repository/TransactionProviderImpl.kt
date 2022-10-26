package org.challenge.customer.repository

import org.springframework.stereotype.Component
import java.util.function.Supplier
import javax.transaction.Transactional

@Component
class TransactionProviderImpl : TransactionProvider {

    @Transactional
    override fun <T> executeAtomically(supplier: Supplier<T>): T = supplier.get()
}