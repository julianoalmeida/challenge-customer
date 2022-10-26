package org.challenge.customer.repository

import java.util.function.Supplier

interface TransactionProvider {
    fun <T> executeAtomically(supplier: Supplier<T>): T
}
