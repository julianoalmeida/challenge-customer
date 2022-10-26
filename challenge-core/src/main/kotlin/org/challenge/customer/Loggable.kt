package org.challenge.customer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.function.Supplier
import kotlin.reflect.full.companionObject

interface Loggable {
    private companion object {
        val factory: LoggableFactory = LoggableFactory()
    }

    val logger: Logger
        get() = factory(Supplier { logger() })
}

class LoggableFactory : (Supplier<Logger>) -> Logger {
    private lateinit var _log: Logger

    override fun invoke(factory: Supplier<Logger>): Logger {
        if (!::_log.isInitialized) {
            _log = factory.get()
        }

        return _log
    }
}

inline fun <reified T : Loggable> T.logger(): Logger = LoggerFactory.getLogger(getClassForLogging(T::class.java))

fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> =
    javaClass.enclosingClass?.takeIf {
        it.kotlin.companionObject?.java == javaClass
    } ?: javaClass