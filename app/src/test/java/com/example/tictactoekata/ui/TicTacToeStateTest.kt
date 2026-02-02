package com.example.tictactoekata.ui

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
}