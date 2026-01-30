package com.example.tictactoekata.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CellTest {
    @Test
    fun `checks every new cell is empty`() {
        val cell = Cell()
        assertTrue(cell.isEmpty())
    }

    @Test
    fun `checks cell with player is not empty`() {
        val cell = Cell(Player.X)
        assertFalse(cell.isEmpty())
        assertEquals(Player.X, cell.player)
    }
}