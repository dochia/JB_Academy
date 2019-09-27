package rationals

import java.math.BigInteger

data class Rational(val value: String) : Comparable<Rational>{
    private val quotient: BigInteger
    private val numerator: BigInteger

    init{
        var newValue: String = value
        if (!value.contains('/'))
            newValue += "/1"
        val splits = newValue.split("/")
        val q = splits[1].toBigInteger()
        val n = splits[0].toBigInteger()
        require(q != 0.toBigInteger())
        val gcd = n.gcd(q)!!
        if (n < 0.toBigInteger() && q < 0.toBigInteger()) {
            numerator = -n / gcd
            quotient = -q / gcd
        }else if (q < 0.toBigInteger()){
               numerator = -n / gcd
                quotient = -q / gcd
        }
            else{
            numerator = n / gcd
            quotient  = q / gcd
        }
    }
    constructor (a : BigInteger, b: BigInteger = 1.toBigInteger()): this(a.toString() + "/" + b.toString())

    operator fun plus(other: Rational): Rational {
        val gcm = quotient * other.quotient
        val num = other.numerator * quotient + numerator * other.quotient
        return normalize(num, gcm)
    }

    operator fun minus(other: Rational): Rational {
        val gcm = quotient * other.quotient
        val num = numerator * other.quotient - quotient * other.numerator
        return normalize(num, gcm)
    }

    operator fun times(other: Rational) : Rational {
        val gcm = quotient * other.quotient
        val num = numerator * other.numerator
        return normalize(num, gcm)
    }

    operator fun div(other: Rational) : Rational{
        val gcm = quotient * other.numerator
        val num =  numerator * other.quotient
        return normalize(num, gcm)
    }

    operator fun unaryMinus() : Rational{
        return Rational (-numerator, quotient)
    }

    operator fun unaryPlus(): Rational{
        return this
    }

    operator fun rangeTo(other: Rational): ClosedRange<Rational>{
        return object : ClosedRange<Rational> {
            override val endInclusive: Rational = other
            override val start: Rational = this@Rational
        }
    }

    override fun equals(other: Any?) : Boolean =
            when {
                this === other -> true
                other !is Rational -> false
                else -> numerator == other.numerator && quotient == other.quotient
            }


    override operator fun compareTo(other: Rational) : Int =
            (numerator * other.quotient - quotient * other.numerator).signum()


    override fun toString(): String {
        if (quotient == 1.toBigInteger())
            return "$numerator"
        return "$numerator/$quotient"
    }
    operator fun inc(): Rational = Rational (numerator + 1.toBigInteger(), quotient)
    operator fun dec(): Rational = Rational(numerator - 1.toBigInteger(), quotient)

    private fun normalize(a: BigInteger, b: BigInteger) : Rational{
        val gcd = a.gcd(b)!!
        val aa = a / gcd
        val bb  = b / gcd
        if (aa < 0.toBigInteger() && bb < 0.toBigInteger()){
            println("boo")
            return Rational(-aa, -bb)}
        return Rational (aa, bb)
    }

    override fun hashCode(): Int {
        var result =  quotient.hashCode()
        result = 31 * result + numerator.hashCode()
        return result
    }
}

infix fun Int.divBy(quotient: Int) : Rational  = Rational(this.toBigInteger(), quotient.toBigInteger())
infix fun Long.divBy(quotient: Long) : Rational = Rational(this.toBigInteger(), quotient.toBigInteger())
infix fun BigInteger.divBy(quotient: BigInteger) : Rational = Rational(this, quotient)

fun String.toRational() : Rational = Rational(this)

fun main() {
    println("Something")
    val half = 1 divBy 2
    val third = 1 divBy 3
    println("half:$half third:$third")


    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}