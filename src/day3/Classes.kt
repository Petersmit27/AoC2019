package day3

import kotlin.math.absoluteValue


data class Coordinate(val x: Int, val y: Int) {
    fun getDistance(): Int {
        return x.absoluteValue + y.absoluteValue
    }
}

data class Move(val direction: Direction, val amount: Int)

enum class Direction { U, D, L, R }