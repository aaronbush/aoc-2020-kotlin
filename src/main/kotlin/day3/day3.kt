package day3

import CircularList
import circular
import loadFile

data class Movement(var horizontal: Int, var vertical: Int) {
    private var horizontalAdvance = horizontal
    private var verticalAdvance = vertical

    fun advance(): Movement {
        horizontal += horizontalAdvance
        vertical += verticalAdvance
        return this
    }
}

fun main() {
    val data = "/day3/input.txt".loadFile().map { it.toList().circular() }
    val movement = Movement(3, 1)
    part1(data, movement)
    part2(data)
}

fun part1(data: List<CircularList<Char>>, movement: Movement): Long {
    var numTrees = 0L
    for (i in 1.until(data.size / movement.vertical)) {
        numTrees += if (data[movement.vertical][movement.horizontal] == '#') 1 else 0
        movement.advance()
    }
    println("part 1 is $numTrees")
    return numTrees
}

fun part2(data: List<CircularList<Char>>) {
    val movements = listOf(
        Movement(1, 1),
        Movement(3, 1),
        Movement(5, 1),
        Movement(7, 1),
        Movement(1, 2),
    )

    val result = movements.map { part1(data, it) }.fold(1L) { total, n -> total * n }
    println("part 2 is $result")
}