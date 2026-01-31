package com.example.tictactoekata.domain

import javax.inject.Inject

class GameEvaluator @Inject constructor() {

    companion object {
        val WINNING_ROWS = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8)
        )
    }

    fun calculateWinner(board: Board): Player? {
        for(row in WINNING_ROWS){
            val (first, second, third) = row
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