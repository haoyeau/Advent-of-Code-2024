package com.example.adventofcode.day5

import java.io.File

object Day5Solution {

    fun addUpMiddleNumberOfCorrectUpdate(): Int{
        // parse rules
        val rules = HashMap<Int, List<Int>> ()
        File("src/main/kotlin/day5/rules_input.txt").readLines().forEach {
            val pages = it.split("|")
            val list = rules[pages[0].toInt()] ?: listOf()
            rules[pages[0].toInt()] = list + pages[1].toInt()
        }
        // parse updates
        val updates = mutableListOf<List<Int>>()
        File("src/main/kotlin/day5/updates_input.txt").readLines().forEach {
            updates.add(it.split(",").map(String::toInt))
        }

        // check order correct
        fun isCorrectOrder(list: List<Int>): Boolean {
            for (i in list.indices) {
                for(j in i + 1 until list.size) {
                    if(rules[list[i]]?.contains(list[j]) == true) continue
                    if(rules[list[j]]?.contains(list[i]) == true) return false
                }
            }
            return true
        }

        return updates.filter { isCorrectOrder(it) }.fold(0){
             acc, list -> acc + list[list.size/2]
        }

    }

}