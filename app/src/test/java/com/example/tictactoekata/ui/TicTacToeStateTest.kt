package com.example.tictactoekata.ui

import com.example.tictactoekata.domain.Board
import com.example.tictactoekata.domain.Player
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TicTacToeStateTest {
    @Test
    fun `default state is initialized correctly`() {
        val state = TicTacToeState()

        assertEquals(Player.X, Player.X)
        assertNull(state.winner)
        assertFalse(state.isDraw)
        assertFalse(state.isGameOver)
        assertNull(state.errorMessage)
        assertTrue(state.board.cells.all { it.player == null })
    }

    @Test
    fun `isCellEnabled returns true when cell is empty`() {
        val state = TicTacToeState()
        val index = 4

        val isEnabled = state.isCellEnabled(index)

        assertTrue(isEnabled)
    }

    @Test
    fun `isCellEnabled returns false when cell is occupied`() {
        val board =  Board().play(0, Player.X)
        val state = TicTacToeState(board, currentPlayer = Player.O)
        val isEnabled = state.isCellEnabled(0)

        assertFalse(isEnabled)
    }
}