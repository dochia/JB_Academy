package mastermind

import java.lang.Integer.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess:String) : Evaluation{
    val right = guess.zip(secret).count{ (x, y) -> x == y}
    val commonLetters = "ABCDEF".sumBy { ch ->
        Math.min(secret.count { it == ch  }, guess.count { it == ch })
    }
    return Evaluation(right, commonLetters - right)
}

/*fun evaluateGuess(secret: String, guess: String): Evaluation {
    val arrG = IntArray(6) { value -> 0 }
    val arrS = IntArray(6) { value -> 0 }
    var right: Int = 0
    for ((index, ch) in guess.withIndex())
        if (ch == secret[index]) {
            right = right.inc()
        } else {
            arrG[ch - 'A']++
            arrS[secret[index] - 'A']++
        }
    var wrong = 0
    for (i in 0..5) wrong += min(arrG[i], arrS[i])
    return Evaluation(right, wrong)
}*/
