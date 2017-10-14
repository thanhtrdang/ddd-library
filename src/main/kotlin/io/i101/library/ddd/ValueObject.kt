package io.i101.library.ddd

/**
 * A value object, as explained in the DDD book.
 *
 * The implementation must be marked "data" which provides hashCode, equals, toString, copy
 */
interface ValueObject<in T> {
    /**
     * Value objects compare by the values of their attributes, they don't have an id.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value object's attributes are the same.
     */
    fun sameValueAs(other: T) = (this == other)
}