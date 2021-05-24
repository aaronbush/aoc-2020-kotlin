package day4

import loadFile

fun main() {
    val data = "/day4/input.txt".loadFile()
        .fold(mutableListOf<MutableSet<Pair<String, String>>>(mutableSetOf())) { accum, line ->
            if (line.isBlank()) {
                accum.apply { add(mutableSetOf()) } // on blank line create new set of field/data
            } else {
                accum.apply {
                    last().apply {
                        addAll(
                            line.split(" ").map { s ->
                                s.split(":", limit = 2).let { Pair(it.first(), it.last()) }
                            })
                    }
                }
            }
        }
    part1(data)
    part2(data)
}

fun part1(data: MutableList<MutableSet<Pair<String, String>>>) {
    val result = validRecords(data).count()
    println("part 1 is $result")
}

private fun validRecords(data: MutableList<MutableSet<Pair<String, String>>>): Sequence<MutableSet<Pair<String, String>>> {
    val requiredFields = setOf("byr", "ecl", "eyr", "hcl", "hgt", "iyr", "pid")
    val ignoreField = "cid"
    return data.filter { set -> set.map { it.first }.toSet() - ignoreField == requiredFields }.asSequence()
}

fun part2(data: MutableList<MutableSet<Pair<String, String>>>) {
    val hgtLayout = "(\\d+)(cm|in)".toRegex()
    val requiredFieldPredicates = mapOf(
        "byr" to { s: String -> s.length == 4 && s.toInt() in 1920..2002 },
        "ecl" to { s: String -> s in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
        "eyr" to { s: String -> s.length == 4 && s.toInt() in 2020..2030 },
        "hcl" to { s: String -> s.matches("#[0-9a-f]{6}".toRegex()) },
        "hgt" to { s: String ->
            hgtLayout.find(s)?.destructured?.let {
                val (num, unit) = it
                when (unit) {
                    "cm" -> num.toInt() in 150..193
                    "in" -> num.toInt() in 59..76
                    else -> false
                }
            } ?: false
        },
        "iyr" to { s: String -> s.length == 4 && s.toInt() in 2010..2020 },
        "pid" to { s: String -> s.length == 9 && s.toIntOrNull() != null },
        "cid" to { s: String -> true },
    )
    val result = validRecords(data).count { rec ->
        rec.all { requiredFieldPredicates[it.first]?.invoke(it.second) ?: false }
    }
    println("part 2 is $result")
}

