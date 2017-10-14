package io.i101.library.ddd

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object ValueObjectTests: Spek({
    describe("sameValueAs") {
        it("Value object 1 should be the same value as value object 1") {
            val vo1 = ValueObjectSample("Pasteur", 114)
            val vo2 = ValueObjectSample("Pasteur", 114)

            assert(vo1.sameValueAs(vo2))
        }
        it("Value object 1 should not be the same value as value object 1") {
            val vo1 = ValueObjectSample("Pasteur", 114)
            val vo2 = ValueObjectSample("Alexander", 239)

            assert(!vo1.sameValueAs(vo2))
        }
    }

    describe("copy") {
        it("should return the same value") {
            val vo1 = ValueObjectSample("Pasteur", 114)
            val vo2 = vo1.copy(street = "Pasteur", number = 114)

            assert(vo1.sameValueAs(vo2))
        }
        it("should return different street") {
            val vo1 = ValueObjectSample("Pasteur", 114)
            val vo2 = vo1.copy(street = "Alexander")

            assert(!vo1.sameValueAs(vo2))
        }
    }
})

private data class ValueObjectSample(val street: String, val number: Int): ValueObject<ValueObjectSample>