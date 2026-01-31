package com.example.tictactoekata.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {

    @Test
    fun `next returns O when current is X`() {
        assertEquals(Player.O, Player.X.next())
    }

    @Test
    fun `next returns X when current is O`() {
        assertEquals(Player.X, Player.O.next())
    }

    @Test
    fun `players should have correct display names`() {
        assertEquals("Player 1", Player.X.playerName)
        assertEquals("Player 2", Player.O.playerName)
    }
}