package io.i101.library.ddd

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions

object EntityTests: Spek({
    describe("id") {
        it("should create entity with id") {
            val id = IDGenerator.next
            val entity = EntitySample(id, name = "EntitySample-1")

            Assertions.assertEquals(id, entity.id)
        }
    }

    describe("sameIdentityAs") {
        it("entity 1 should be the same as entity 2 by id value") {
            val id = IDGenerator.next
            val entity1 = EntitySample(id, "name-1")
            val entity2 = EntitySample(id, "name-2")

            assert(entity1.sameIdentityAs(entity2))
        }

        it("entity 1 should not be the same as entity 2") {
            val id1 = IDGenerator.next
            val id2 = IDGenerator.next
            val entity1 = EntitySample(id1, "name-1")
            val entity2 = EntitySample(id2, "name-2")

            assert(!entity1.sameIdentityAs(entity2))
        }
    }
})

internal class EntitySample(override val id: String, val name: String): EntitySupport<EntitySample, String>()