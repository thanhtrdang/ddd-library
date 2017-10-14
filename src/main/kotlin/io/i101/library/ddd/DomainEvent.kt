package io.i101.library.ddd

/**
 * A domain event is something that is unique, but does not have a lifecycle.
 * The id may be explicit, for example the sequence number of a payment,
 * or it could be derived from various aspects of the event such as where, when and what has happened.
 *
 * Config of("id") if the id be explicit.
 *
 * The implementation should be marked "data" which provides hashCode, equals, toString, copy
 */
interface DomainEvent<in T> {
    /**
     * @param other The other domain event.
     * @return <code>true</code> if the given domain event and this event are regarded as being the same event.
     */
    fun sameEventAs(other: T) = (this == other)
}