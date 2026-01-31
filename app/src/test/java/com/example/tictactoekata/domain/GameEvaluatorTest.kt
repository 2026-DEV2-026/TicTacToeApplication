package com.example.tictactoekata.domain

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GameEvaluatorTest {

    companion object{
        @JvmStatic
        fun provideWinningRows() : List<List<Int>> = GameEvaluator.WINNING_ROWS
    }
    private val evaluator = GameEvaluator()

    @Test
    fun `no winner on empty board`(){
        assertNull(evaluator.calculateWinner(Board()))
    }

    @ParameterizedTest(name = "X wins on row : {0}")
    @MethodSource("provideWinningRows")
    fun `calculates winner on all rows`(winningIndices : List<Int>){
        val board = winningIndices.fold(Board()) { currentBoard, index ->
            currentBoard.play(index, Player.X)
        }

        assertEquals(Player.X,evaluator.calculateWinner(board))
    }
}