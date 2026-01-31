package com.example.tictactoekata.domain

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertTrue

class BoardTest {

    @ParameterizedTest(name = "Board should be empty")
    @ValueSource(ints = [0,1,2,3,4,5,6,7,8])
    fun `new board is completely empty for all cells`(cellIndex : Int) {
        val board = Board()
        assertTrue(board.get(cellIndex).isEmpty())
    }
}