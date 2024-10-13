package coroutines.sequences.prime

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

val primes: Sequence<BigInteger> = sequence {
    var current = BigInteger.valueOf(1L)
    while (true) {
        current = current.inc()
        if (isPrime(current)) {
            yield(current)
        }
    }
}

private fun isPrime(arg: BigInteger): Boolean {
    var current = BigInteger.valueOf(2L)
    val limit = arg.sqrt().inc()
    while (true) {
        if (current >= limit) return true
        if (arg.mod(current) == BigInteger.ZERO) return false
        current = current.inc()
    }
}

class PrimesTest {
    @Test
    fun `should calculate the first 10 prime numbers`() {
        val primes = primes.take(10).toList()
        val expected = listOf(
            BigInteger("2"),
            BigInteger("3"),
            BigInteger("5"),
            BigInteger("7"),
            BigInteger("11"),
            BigInteger("13"),
            BigInteger("17"),
            BigInteger("19"),
            BigInteger("23"),
            BigInteger("29"),
        )
        assertEquals(expected, primes)
    }

    @Test(timeout = 1000)
    fun `should calculate 1000'th prime number`() {
        val prime = primes.drop(1000).first()
        assertEquals(BigInteger("7927"), prime)
    }
}
