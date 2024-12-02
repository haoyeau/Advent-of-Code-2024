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
       return reports.count { list -> isReportSafe(list) }
    }

    private fun isReportSafe(list: List<Int>): Boolean {
        // check all increasing
        val isIncreasing = list.zipWithNext().all { pair -> pair.second - pair.first in 1..3 }
        if(isIncreasing){
            return true
        }
        // check all decreasing
        return list.zipWithNext().all { pair -> pair.first - pair.second in 1..3 }
    }

}