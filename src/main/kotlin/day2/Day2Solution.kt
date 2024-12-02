package com.example.adventofcode.day2

import java.io.File

object Day2Solution {


    fun countSafeReports(): Int{
        val reports = mutableListOf<List<Int>>()
        // read input
        File("src/main/kotlin/day2/input.txt").forEachLine {
            reports.add(it.split(" ").map { number -> number.toInt() })
        }
        // count safe report
       return reports.count { list -> isAllIncreasing(list) || isAllDecreasing(list) }
    }

    private fun isAllIncreasing(list: List<Int>): Boolean {
        return list.zipWithNext().all { pair -> pair.second - pair.first in 1..3 }
    }

    private fun isAllDecreasing(list: List<Int>): Boolean {
        return list.zipWithNext().all { pair -> pair.first - pair.second in 1..3 }
    }

    private fun isSafeWithSingleBadLevel(list: List<Int>, safePredicate: (value: Int) -> Boolean): Boolean {
        val diffs = list.zipWithNext { a, b -> a - b }
        // all good
        if(diffs.count { !safePredicate(it) } == 0) return true
        // find first bad level
        val badLevelIndex = diffs.indexOfFirst { !safePredicate(it) }
        // drop the last one
        if(badLevelIndex == diffs.size -1) return true
        // drop the first one
        if(badLevelIndex == 0 && diffs.subList(1, diffs.size).all(safePredicate)) return true
        // merge previous
        if(badLevelIndex != 0
            && safePredicate(diffs[badLevelIndex] + diffs[badLevelIndex - 1])
            && diffs.subList(badLevelIndex+1, diffs.size).all(safePredicate)) {
            return true
        }
        // merge next
        if (safePredicate(diffs[badLevelIndex] + diffs[badLevelIndex + 1])
            && diffs.subList(badLevelIndex+2, diffs.size).all(safePredicate)) {
            return true
        }
        return false
    }
    fun countSafeReportsWithSingleBadLevel(): Int {
        val reports = mutableListOf<List<Int>>()
        // read input
        File("src/main/kotlin/day2/input.txt").forEachLine {
            reports.add(it.split(" ").map { number -> number.toInt() })
        }
        // count safe report
        return reports.count { list -> isSafeWithSingleBadLevel(list){ value -> value in 1..3 } || isSafeWithSingleBadLevel(list){ value -> value in -3..-1} }
    }

}