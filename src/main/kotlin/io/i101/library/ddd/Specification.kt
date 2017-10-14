package io.i101.library.ddd

/**
 * A specification, as explained in the DDD book.
 *
 * Use !, AND, OR for readability
 */
interface Specification<T> {
    infix fun isSatisfiedBy(obj: T): Boolean

    infix fun AND(other: Specification<T>): Specification<T> = AndSpecification(this, other)
    infix fun OR(other: Specification<T>): Specification<T> = OrSpecification(this, other)

    operator fun not(): Specification<T> = NotSpecification(this)
    infix fun and(other: Specification<T>): Specification<T> = AND(other)
    infix fun or(other: Specification<T>): Specification<T> = OR(other)
}

private data class NotSpecification<T>(val other: Specification<T>): Specification<T> {
    override fun isSatisfiedBy(obj: T) = !other.isSatisfiedBy(obj)
}
private data class AndSpecification<T>(val spec1: Specification<T>, val spec2: Specification<T>): Specification<T> {
    override fun isSatisfiedBy(obj: T) = spec1.isSatisfiedBy(obj) && spec2.isSatisfiedBy(obj)
}
private data class OrSpecification<T>(val spec1: Specification<T>, val spec2: Specification<T>): Specification<T> {
    override fun isSatisfiedBy(obj: T) = spec1.isSatisfiedBy(obj) || spec2.isSatisfiedBy(obj)
}