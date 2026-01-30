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
}