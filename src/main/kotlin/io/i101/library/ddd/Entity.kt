package io.i101.library.ddd

/**
 * An entity, as explained in the DDD book.
 *
 * TODO Remove {@link EntitySupport} and create @Entity later
 */
interface Entity<T: Entity<T, ID>, ID> {
    /**
     * Entities have an id.
     *
     * @return The id of this entity.
     */
    val id: ID

    /**
     * Entities compare by id, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    fun sameIdentityAs(other: T): Boolean
}

/**
 * If technical id is the same business id, go with this {@link EntitySupport},
 * else e.g. your business id is username, then your entity should implement {@link Entity} interface above.
 */
abstract class EntitySupport<T: Entity<T, ID>, ID>: Entity<T, ID> {
    final override fun sameIdentityAs(other: T) = (id == other.id)

    @Suppress("UNCHECKED_CAST")
    final override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || this.javaClass != other.javaClass) {
            return false
        }

        return sameIdentityAs(other as T)
    }

    final override fun hashCode() = id!!.hashCode()

    override fun toString() = "${javaClass.simpleName}(ID=$id)"
}