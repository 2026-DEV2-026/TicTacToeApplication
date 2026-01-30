package com.example.tictactoekata.domain

import kotlin.test.Test
import kotlin.test.assertTrue

class CellTest {
    @Test
    fun `checks every new cell is empty`() {
        val cell = Cell()
        assertTrue(cell.isEmpty())
    }
}