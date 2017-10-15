package io.i101.library.ddd

/**
 * A specification, as explained in the DDD book.
 *
 * Use !, AND, OR for readability.
 * Override isSatisfiedBy only, never override !, AND, OR.
 */
interface Specification<T> {
    infix fun isSatisfiedBy(obj: T): Boolean

    infix fun AND(other: Specification<T>): Specification<T> = object: Specification<T> {
        override fun isSatisfiedBy(obj: T): Boolean = this@Specification.isSatisfiedBy(obj) && other.isSatisfiedBy(obj)
    }
    infix fun OR(other: Specification<T>): Specification<T> = object: Specification<T> {
        override fun isSatisfiedBy(obj: T): Boolean = this@Specification.isSatisfiedBy(obj) || other.isSatisfiedBy(obj)
    }

    operator fun not(): Specification<T> = object: Specification<T> {
        override fun isSatisfiedBy(obj: T): Boolean = !this@Specification.isSatisfiedBy(obj)
    }

    infix fun and(other: Specification<T>): Specification<T> = AND(other)
    infix fun or(other: Specification<T>): Specification<T> = OR(other)
}