package com.example.adventofcode.day1

import java.io.File
import kotlin.math.abs

object Solution {

    fun calculateDistanceBetweenList(): Int{
        // read input
        val (leftList, rightList) = readList()
        // sort list
        leftList.sort()
        rightList.sort()
        // accumulate distance
        return leftList.zip(rightList).fold(0){
            acc, pair -> acc + abs(pair.first - pair.second)
        }
    }

    fun calculateSimilarityScore(): Int{
        // read input
        val (leftList, rightList) = readList()
        // accumulate score
        return leftList.fold(0){
            acc, i -> acc + i*rightList.count { it == i }
        }
    }

    private fun readList(): Pair<MutableList<Int>, MutableList<Int>>{
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()
        // parse input file
        File("src/main/kotlin/day1/input.txt").forEachLine { line ->
            val numbers = line.split("\\s+".toRegex())
            leftList.add(numbers[0].toInt())
            rightList.add(numbers[1].toInt())
        }
        return (leftList to rightList)
    }

}