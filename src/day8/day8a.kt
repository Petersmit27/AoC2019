@file:Suppress("DuplicatedCode")

package day8

import java.io.File


private const val WIDTH = 25
private const val HEIGHT = 6

fun main() {
    val layers = ArrayList<List<List<Int>>>()

    val numbers = File("./src/day8/input.txt").readText().split("").drop(1)
    var counter = 0

    //too overcomplicated for part a but oh well ¯\_(ツ)_/¯
    while (counter < numbers.size) {
        val layer = ArrayList<List<Int>>()

        try {
            for (i in 0 until HEIGHT) {
                layer.add(numbers.subList(counter, counter + WIDTH).map { it.toInt() })
                counter += WIDTH
            }
        } catch (e: IndexOutOfBoundsException) {
            break
        }
        layers.add(layer)
    }


    val zeroCounts = ArrayList<Int>()
    for (layer in layers) {
        zeroCounts.add(layer.flatten().count { it == 0 })
    }
    val lowestZeroes = layers[zeroCounts.indexOf(zeroCounts.min())]
    println("Amount of zeroes: ${lowestZeroes.flatten().count { it == 0 }}")
    println("Amount of ones: ${lowestZeroes.flatten().count { it == 1 }}")
    println("Amount of twos: ${lowestZeroes.flatten().count { it == 2 }}")
    println("Ones * twos == ${lowestZeroes.flatten().count { it == 1 } * lowestZeroes.flatten().count { it == 2 }}")

}





