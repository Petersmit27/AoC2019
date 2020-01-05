package day10

import java.io.File

val map = ArrayList<Coordinate>()
fun main() {


    val fileString = File("./src/day10/testInput.txt").readLines()

    for ((y, row) in fileString.withIndex()) for ((x, char) in row.withIndex()) {
        map.add(Coordinate(x, y, char == '#'))
    }
    val dest = getCoordinateAt(2, 0)
    val origin = getCoordinateAt(3, 4)
    println(isBlocked(dest, origin))
    println(isBlocked(origin, dest))
//    isBlocked(getCoordinateAt())


//    val origin = getCoordinateAt(5, 3)
//    val dest = getCoordinateAt(3, 1)

//    println(origin)
//    println(dest)
//
//    println(isBlocked(dest, origin))
//    println(checkSurroundingAsteroids(origin))
//    println(checkSurroundingAsteroids(getCoordinateAt(4, 2)))
//    println(checkSurroundingAsteroids(getCoordinateAt(4, 3)))
//    println(checkSurroundingAsteroids(getCoordinateAt(4, 4)))
//    for (coordinate in map) {
//
//    }


}

fun isBlocked(dest: Coordinate, origin: Coordinate): Boolean {
    try {

        val slope = getSlope(dest, origin)
        val yOffset = getYOffset(dest, origin)


        var currentX = if (dest.x < origin.x) dest.x else origin.x
        val endX = if (dest.x < origin.x) origin.x else dest.x
        while (currentX != endX) {
            currentX++
            val currentY: Float = slope * currentX + yOffset
            if (currentY % 1 == 0F &&
                getCoordinateAt(currentX, currentY.toInt()).hasAsteroid &&
                currentX != endX
            ) {
                return true
            }
        }
    } catch (e: ArithmeticException) {
        //if we get here it means that the points are above each other
        var currentY = if (dest.y < origin.y) dest.y else origin.y
        val endY = if (dest.y < origin.y) origin.y else dest.y
        while (currentY != endY) {
            currentY++
            if (getCoordinateAt(dest.x, currentY).hasAsteroid && currentY != endY) {
                return true
            }
        }
    }




    return false
}

fun getSlope(coordinate1: Coordinate, coordinate2: Coordinate) =
    (coordinate2.y - coordinate1.y).toFloat() / (coordinate2.x - coordinate1.x).toFloat()

fun getYOffset(coordinate1: Coordinate, coordinate2: Coordinate) =
    (coordinate2.x * coordinate1.y - coordinate1.x * coordinate2.y).toFloat() / (coordinate2.x - coordinate1.x).toFloat()

fun checkSurroundingAsteroids(coordinate: Coordinate) =
    map.count { it.hasAsteroid && it != coordinate && !isBlocked(it, coordinate) }

fun getCoordinateAt(x: Int, y: Int) = map.first { it.x == x && it.y == y }


data class Coordinate(val x: Int, val y: Int, val hasAsteroid: Boolean)
