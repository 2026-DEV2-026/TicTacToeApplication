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

        @JvmStatic
        fun provideWinningColumns(): List<List<Int>> = GameEvaluator.WINNING_COLUMNS

        @JvmStatic
        fun provideWinningDiagonals() : List<List<Int>> = GameEvaluator.WINNING_DIAGONALS
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

    @ParameterizedTest(name = "O wins on column : {0}")
    @MethodSource("provideWinningColumns")
    fun `calculates winner on all columns`(winningIndices : List<Int>){
        val board = winningIndices.fold(Board()) { currentBoard, index ->
            currentBoard.play(index, Player.O)
        }

        assertEquals(Player.O,evaluator.calculateWinner(board))
    }

    @ParameterizedTest(name = "X wins on diagonals : {0}")
    @MethodSource("provideWinningDiagonals")
    fun `calculates winner on all diagonals`(winningIndices : List<Int>){
        val board = winningIndices.fold(Board()) { currentBoard, index ->
            currentBoard.play(index, Player.X)
        }

        assertEquals(Player.X,evaluator.calculateWinner(board))
    }
}