package com.example.tictactoekata.domain

import kotlin.test.Test
import kotlin.test.assertNull

class GameEvaluatorTest {
    private val evaluator = GameEvaluator()

    @Test
    fun `no winner on empty board`(){
        assertNull(evaluator.calculateWinner(Board()))
    }
}