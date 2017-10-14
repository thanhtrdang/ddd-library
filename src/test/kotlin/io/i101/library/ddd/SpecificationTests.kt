package io.i101.library.ddd

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object SpecificationTests: Spek({
    val anyObject = Any()
    describe("isSatisfiedBy") {
        it("true") {
            val trueSpec = TrueSpec<Any>()

            assert(trueSpec.isSatisfiedBy(anyObject))
        }
        it("false") {
            val falseSpec = FalseSpec<Any>()

            assert(!falseSpec.isSatisfiedBy(anyObject))
        }
    }

    describe("and") {
        val falseSpec1 = FalseSpec<Any>()
        val falseSpec2 = FalseSpec<Any>()
        val trueSpec1 = TrueSpec<Any>()
        val trueSpec2 = TrueSpec<Any>()

        it("true AND true => true") {
            val andSpec = trueSpec1 AND trueSpec2

            assert(andSpec.isSatisfiedBy(anyObject))
        }
        it("false AND true => false") {
            val andSpec1 = falseSpec1 AND trueSpec1
            val andSpec2 = trueSpec1 AND falseSpec1

            assert(!andSpec1.isSatisfiedBy(anyObject))
            assert(!andSpec2.isSatisfiedBy(anyObject))
        }
        it("false AND false => false") {
            val andSpec = falseSpec1 AND falseSpec2

            assert(!andSpec.isSatisfiedBy(anyObject))
        }
    }

    describe("or") {
        val falseSpec1 = FalseSpec<Any>()
        val falseSpec2 = FalseSpec<Any>()
        val trueSpec1 = TrueSpec<Any>()
        val trueSpec2 = TrueSpec<Any>()

        it("true OR true => true") {
            val orSpec = trueSpec1 OR trueSpec2

            assert(orSpec.isSatisfiedBy(anyObject))
        }
        it("false OR true => true") {
            val orSpec1 = falseSpec1 OR trueSpec1
            val orSpec2 = trueSpec1 OR falseSpec1

            assert(orSpec1.isSatisfiedBy(anyObject))
            assert(orSpec2.isSatisfiedBy(anyObject))
        }
        it("false OR false => false") {
            val orSpec = falseSpec1 AND falseSpec2

            assert(!orSpec.isSatisfiedBy(anyObject))
        }
    }

    describe("not") {
        it("NOT true => false") {
            val notTrueSpec = !TrueSpec<Any>()

            assert(!notTrueSpec.isSatisfiedBy(anyObject))
        }
        it("NOT false => true") {
            val notFalseSpec = !FalseSpec<Any>()
            assert(notFalseSpec.isSatisfiedBy(anyObject))
        }
    }
})

private class TrueSpec<T>: Specification<T> {
    override fun isSatisfiedBy(obj: T) = true
}

private class FalseSpec<T>: Specification<T> {
    override fun isSatisfiedBy(obj: T) = false
}