package com.example.adventofcode.day1

import java.io.File
import kotlin.math.abs

object Solution {

    fun calculateDistanceBetweenList(): Int{
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        // read input
        File("src/main/kotlin/day1/input.txt").forEachLine { line ->
            val numbers = line.split("\\s+".toRegex())
            leftList.add(numbers[0].toInt())
            rightList.add(numbers[1].toInt())
        }
        // sort list
        leftList.sort()
        rightList.sort()
        // accumulate distance
        return leftList.zip(rightList).fold(0){
            acc, pair -> acc + abs(pair.first - pair.second)
        }
    }

}