package day2

import loadFile

data class Policy(val min: Int, val max: Int, val character: Char, val password: String)

fun main() {
    val layout = "(\\d+)-(\\d+)\\s(\\w): (\\w+)".toRegex()
    val data = "/day2/input.txt".loadFile().map {
        layout.matchEntire(it)
            ?.destructured
            ?.let { (min, max, character, pass) -> Policy(min.toInt(), max.toInt(), character.first(), pass) }
            ?: throw IllegalArgumentException("Failed to parse input")
    }
    part1(data)
    part2(data)
}

fun part1(policies: List<Policy>) {
    val result = policies.count { policy ->
        policy.password.count {
            it == policy.character
        } in policy.min..policy.max
    }
    println(result)
}

fun part2(policies: List<Policy>) {
    val result = policies.count { policy ->
        (policy.password[policy.min - 1] == policy.character) xor (policy.password[policy.max - 1] == policy.character)
    }
    println(result)
}

