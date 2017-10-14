package io.i101.library.ddd

import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

interface Repository<T: Entity<T, ID>, ID> {
    fun find(id: ID): Mono<T>
    fun store(entity: T): Mono<T>
}

class InMemRepository<T: Entity<T, ID>, ID>: Repository<T, ID> {
    private val memCache = ConcurrentHashMap<ID, T>()

    override fun find(id: ID): Mono<T> = Mono.justOrEmpty(memCache[id])

    override fun store(entity: T): Mono<T> {
        memCache[entity.id] = entity
        return Mono.just(entity)
    }
}