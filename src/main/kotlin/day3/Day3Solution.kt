package com.example.adventofcode.day3

import java.io.File

object Day3Solution {

    // part1
    fun addUpMulInstructions(): Int{
        val memoryContent = File("src/main/kotlin/day3/input.txt").readText()
        return Regex("""mul\((\d+),(\d+)\)""").findAll(memoryContent).fold(0){
            acc, matchResult ->
            acc + matchResult.groupValues[1].toInt()*matchResult.groupValues[2].toInt()
        }
    }

}