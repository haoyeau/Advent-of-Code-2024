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

    // part2
    fun addUpEnabledInstructions(): Int{
        val memoryContent = File("src/main/kotlin/day3/input.txt").readText()
        var enabled = true
        return Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""").findAll(memoryContent).fold(0){
            acc, matchResult ->
            when (matchResult.value){
                "do()" -> {
                    enabled = true
                    acc
                }
                "don't()" -> {
                    enabled = false
                    acc
                }
                else -> {
                    if (enabled) acc + matchResult.groupValues[1].toInt()*matchResult.groupValues[2].toInt()
                    else acc
                }
            }
        }
    }

}