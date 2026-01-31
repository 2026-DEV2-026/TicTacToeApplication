package com.example.tictactoekata.domain

import javax.inject.Inject

class GameEvaluator @Inject constructor() {

    companion object {
        val WINNING_ROWS = listOf(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8))
        val WINNING_COLUMNS = listOf(listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8))
        val WINNING_DIAGONALS = listOf(listOf(0, 4, 8), listOf(2, 4, 6))

        private val WINNING_PATTERNS = WINNING_ROWS + WINNING_COLUMNS + WINNING_DIAGONALS
    }

    fun calculateWinner(board: Board): Player? {
        return WINNING_PATTERNS.firstNotNullOfOrNull { (first, second, third) ->
            board.cells[first].player?.takeIf { player ->
                player == board.cells[second].player && player == board.cells[third].player
            }
        }
    }

    fun isDraw(board: Board): Boolean {
        return board.isFull() && calculateWinner(board) == null
    }
}