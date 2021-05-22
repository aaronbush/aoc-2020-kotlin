package day6

import lineCombiner
import loadFile

fun main() {
    val data = "/day6/input.txt".loadFile()
    part1(data)
    part2(data)
}

fun part2(data: List<String>) {
    val op = lineCombiner({ l: MutableList<Pair<Int, MutableList<Char>>> -> l.add(0 to mutableListOf()) })
    { s, l ->
        val prev = l.last()
        l[l.size - 1] = prev.first + 1 to s.toMutableList().apply { addAll(prev.second) }
        l
    }
    val records = data.fold(mutableListOf(0 to mutableListOf()), op)
    val result = records.map { it.second.groupingBy { c -> c }.eachCount().filterValues { i -> i == it.first } }
        .sumOf { it.size }
    println("Part 2 is $result")
}

fun part1(data: List<String>) {
    val op = lineCombiner({ l: MutableList<MutableSet<Char>> -> l.add(mutableSetOf()) })
    { s, l ->
        l.last().addAll(s.asIterable())
    }
    val result = data.fold(mutableListOf(mutableSetOf()), op)
        .fold(0) { acc, answers -> acc + answers.size }
    println("part 1 is $result")
}