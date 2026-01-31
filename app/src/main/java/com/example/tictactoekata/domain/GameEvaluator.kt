package com.example.tictactoekata.domain

import javax.inject.Inject

class GameEvaluator @Inject constructor() {

    companion object {
        val WINNING_ROWS = listOf(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8))
        val WINNING_COLUMNS = listOf(listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8))

        val WINNING_DIAGONALS = listOf(listOf(0, 4, 8), listOf(2, 4, 6))
    }

    fun calculateWinner(board: Board): Player? {
        for (row in WINNING_ROWS) {
            val (first, second, third) = row
            val cellFirst = board.cells[first]
            if (cellFirst.player != null &&
                cellFirst.player == board.cells[second].player &&
                cellFirst.player == board.cells[third].player
            ) {
                return cellFirst.player
            }
        }
        for (col in WINNING_COLUMNS) {
            val (first, second, third) = col
            val cellFirst = board.cells[first]
            if (cellFirst.player != null &&
                cellFirst.player == board.cells[second].player &&
                cellFirst.player == board.cells[third].player
            ) {
                return cellFirst.player
            }
        }

        for(diagonal in WINNING_DIAGONALS){
            val (first,second,third) = diagonal
            val cellFirst = board.cells[first]
            if(cellFirst.player != null &&
                cellFirst.player == board.cells[second].player &&
                cellFirst.player == board.cells[third].player){
                return cellFirst.player
            }
        }
        return null
    }
}