package com.example.tictactoekata.domain

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BoardTest {

    @ParameterizedTest(name = "Board should be empty")
    @ValueSource(ints = [0,1,2,3,4,5,6,7,8])
    fun `new board is completely empty for all cells`(cellIndex : Int) {
        val board = Board()
        assertTrue(board.get(cellIndex).isEmpty())
    }

    @Test
    fun `play returns a new board instance with the move applied`() {
        val originalBoard = Board()
        val moveIndex = 0
        val newBoard = originalBoard.play(moveIndex, Player.X)

        assertTrue(originalBoard.get(moveIndex).isEmpty())
        assertEquals(Player.X, newBoard.get(moveIndex).player)
    }

    @Test
    fun `throws exception if index is negative`() {
        assertFailsWith<IllegalArgumentException> {
            Board().play(-1, Player.X)
        }
    }

    @Test
    fun `throws exception if index is too large`() {
        assertFailsWith<IllegalArgumentException> {
            Board().play(9, Player.X)
        }
    }

    @Test
    fun `throws exception if cell is already occupied`() {
        assertFailsWith<IllegalArgumentException> {
            val newBoard = Board().play(0, Player.X)
            newBoard.play(0, Player.O)
        }
    }

    @Test
    fun `isFull returns false when board has empty cells`(){
        val board = Board().play(0, Player.X)
        assertFalse(board.isFull())
    }

    @Test
    fun `isFull returns true when all cells are taken`() {
        var board = Board()
        for (i in 0 until TOTAL_CELLS) {
            board = board.play(i, Player.X)
        }
        assertTrue(board.isFull())
    }
}