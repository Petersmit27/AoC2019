package day3

import day3.Direction.*
import java.io.File
import kotlin.math.absoluteValue

fun main() {

    val allMoves = File("./src/day3/input.txt").readText()
    val moves1 = allMoves.split("\n")[0].split(",").map {
        Move(
            when {
                it.substring(0, 1) == "U" -> U
                it.substring(0, 1) == "L" -> L
                it.substring(0, 1) == "R" -> R
                else -> D
            }, it.substring(1).toInt()
        )
    }
    val moves2 = allMoves.split("\n")[1].split(",").map {
        Move(
            when {
                it.substring(0, 1) == "U" -> U
                it.substring(0, 1) == "L" -> L
                it.substring(0, 1) == "R" -> R
                else -> D
            }, it.substring(1).toInt()
        )
    }


    val intersections = ArrayList<Coordinate>()
    val geweesteCoordinaten = ArrayList<Coordinate>()
    var lastCoordinate = Coordinate(0, 0)
    var newCoordinate = Coordinate(Int.MAX_VALUE, Int.MAX_VALUE)


    moves1.forEach {
        when (it.direction) {
            U -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y + i)
                    geweesteCoordinaten.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            R -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x + i, lastCoordinate.y)
                    geweesteCoordinaten.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            D -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y - i)
                    geweesteCoordinaten.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            L -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x - i, lastCoordinate.y)
                    geweesteCoordinaten.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
        }
    }

    lastCoordinate = Coordinate(0, 0)

    moves2.forEach {
        when (it.direction) {
            U -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y + i)
                    if (geweesteCoordinaten.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                }
                lastCoordinate = newCoordinate
            }
            R -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x + i, lastCoordinate.y)
                    if (geweesteCoordinaten.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                }
                lastCoordinate = newCoordinate
            }
            D -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y - i)
                    if (geweesteCoordinaten.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                }
                lastCoordinate = newCoordinate
            }
            L -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x - i, lastCoordinate.y)
                    if (geweesteCoordinaten.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                }
                lastCoordinate = newCoordinate
            }
        }
    }


    println(intersections.map { it.getDistance() }.min())
}
