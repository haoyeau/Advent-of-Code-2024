package com.example.adventofcode.day4

import java.io.File

object Day4Solution {

    private const val SEARCH_WORD = "XMAS"

    // part1
    fun countWord(): Int {
        // read input
        val grid = File("src/main/kotlin/day4/input.txt").readLines();
        val rows = grid.size
        val cols = grid[0].length
        val directions = listOf(
            Pair(0, 1),   // Right
            Pair(1, 0),   // Down
            Pair(1, 1),   // Diagonal down-right
            Pair(1, -1),  // Diagonal down-left
            Pair(0, -1),  // Left
            Pair(-1, 0),  // Up
            Pair(-1, -1), // Diagonal up-left
            Pair(-1, 1)   // Diagonal up-right
        )

        var count = 0

        // ensure location inbound
        fun isValid(x: Int, y: Int) = x in 0 until rows && y in 0 until cols

        // find word in specific direction
        fun findWord(x: Int, y: Int, dx: Int, dy: Int): Boolean {
            for (i in SEARCH_WORD.indices) {
                val nx = x + i * dx
                val ny = y + i * dy
                if (!isValid(nx, ny) || grid[nx][ny] != SEARCH_WORD[i]) {
                    return false
                }
            }
            return true
        }

        // find first character, then search in directions
        for (x in 0 until rows) {
            for (y in 0 until cols) {
                if (grid[x][y] == SEARCH_WORD[0]) {
                    for ((dx, dy) in directions) {
                        if (findWord(x, y, dx, dy)) {
                            count++
                        }
                    }
                }
            }
        }
        return count;
    }

}