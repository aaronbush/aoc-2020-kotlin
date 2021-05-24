package day1

import loadFile

const val target = 2020

fun main() {
    val data = "/day1/input.txt".loadFile().map(String::toInt).toSet()
    part1m2(data)
}

fun part1m2(data: Set<Int>) {
    val result = data.first { i -> (data - i).contains(target - i) }
    println("part 1 is $result * ${target - result} == ${result * (target - result)}")
}
