package com.example.tictactoekata.ui

import com.example.tictactoekata.domain.Board
import com.example.tictactoekata.domain.Player
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.intArrayOf
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
    fun `isCellEnabled returns false when cell is occupied by X`() {
        val board =  Board().play(0, Player.X)
        val state = TicTacToeState(board = board)
        val isEnabled = state.isCellEnabled(0)

        assertFalse(isEnabled)
    }

    @Test
    fun `isCellEnabled returns false when cell is occupied by O`() {
        val board =  Board().play(8, Player.O)
        val state = TicTacToeState(board = board)
        val isEnabled = state.isCellEnabled(8)

        assertFalse(isEnabled)
    }

    @ParameterizedTest(name = "Cells on board should be disabled")
    @ValueSource(ints = [0,1,2,3,4,5,6,7,8])
    fun `isCellEnabled returns false when game is over`(cellIndex : Int) {
        val state = TicTacToeState(
            winner = Player.X,
            isGameOver = true
        )

        assertFalse(state.isCellEnabled(cellIndex))
    }
}