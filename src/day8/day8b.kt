@file:Suppress("DuplicatedCode")

package day8

import java.io.File


private const val WIDTH = 25
private const val HEIGHT = 6

fun main() {
    val layers = ArrayList<List<List<Int>>>()

    val numbers = File("./src/day8/input.txt").readText().split("").drop(1)
    var counter = 0

    //fill array of layers
    try {
        while (counter < numbers.size) {
            val layer = ArrayList<List<Int>>()
            for (i in 0 until HEIGHT) {
                layer.add(numbers.subList(counter, counter + WIDTH).map { it.toInt() })
                counter += WIDTH
            }
            layers.add(layer)
        }
    } catch (e: IndexOutOfBoundsException) {
    }

    //Fill the final image with transparent characters
    val image = ArrayList<ArrayList<Char>>()
    for (i in 0 until HEIGHT) {
        val row = ArrayList<Char>()
        for (j in 0 until WIDTH) {
            row.add(' ')
        }
        image.add(row)
    }

    //fill in the final image
    for (layer in layers) {
        for ((rowIndex, row) in layer.withIndex()) {
            for ((columnIndex, number) in row.withIndex()) {
                if (image[rowIndex][columnIndex] == ' ' && number != 2) {
                    if (number == 1)
                        image[rowIndex][columnIndex] = '□'
                    else
                        image[rowIndex][columnIndex] = '■'
                }
            }
        }
    }

    for (row in image) {
        print(row)
        println()
    }
}





