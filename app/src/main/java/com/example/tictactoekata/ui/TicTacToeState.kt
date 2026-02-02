package com.example.tictactoekata.ui

import com.example.tictactoekata.domain.Board
import com.example.tictactoekata.domain.Player

data class TicTacToeState(
    val board: Board = Board(),
    val currentPlayer: Player = Player.X,
    val winner: Player? = null,
    val isDraw: Boolean = false,
    val isGameOver : Boolean = false,
    val errorMessage: String? = null,
){
    fun isCellEnabled(index: Int): Boolean {
        return board.cells[index].player == null
    }
}