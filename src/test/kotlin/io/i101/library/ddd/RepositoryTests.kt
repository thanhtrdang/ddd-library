package io.i101.library.ddd

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import reactor.test.test

object InMemRepositoryTests: Spek({
    val id = IDGenerator.next
    val entity = EntitySample(id, "name-1")

    val repository = InMemRepository<EntitySample, String>()
    val storedEntityMono = repository.store(entity)

    describe("find") {
        it("should return entity with given id") {
            val entityMono = repository.find(id)

            entityMono.test()
                    .expectNext(entity)
                    .verifyComplete()
        }
        it("should return empty Mono") {
            val entityMono = repository.find(IDGenerator.next)

            entityMono.test()
                    .verifyComplete()
        }
    }

    describe("store") {
        it("should store entity in the memory") {
            storedEntityMono.test()
                    .expectNext(entity)
                    .verifyComplete()
        }
    }
})