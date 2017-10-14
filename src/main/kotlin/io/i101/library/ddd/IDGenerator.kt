package io.i101.library.ddd

import java.util.concurrent.atomic.AtomicLong

/**
 * ID generator is based on current time (millis) and JVM/app/service code (3 alphanumeric characters)
 */
object IDGenerator {
    /**
     * Three characters that used for different JVM/app/service. Default is 000
     * E.g. 007 (preferred) or ACS, A08,...
     */
    private var code = "000"
    private val current = AtomicLong(System.currentTimeMillis())

    /**
     * Init ID generator in a single JVM/app/service, good time is at starting your JVM/app/service.
     * If your JVM/app/service is monolithic, then just ignore this method.
     */
    fun init(code: String) {
        //TODO("Assert code is alphanumeric")
        assert(code.length == 3)

        IDGenerator.code = code
    }

    val next: String get() = "${code}${current.getAndIncrement()}"
}