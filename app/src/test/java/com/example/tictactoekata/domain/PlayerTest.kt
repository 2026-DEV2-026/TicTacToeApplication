package com.example.tictactoekata.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {

    @Test
    fun `next returns O when current is X`() {
        assertEquals(Player.O, Player.X.next())
    }
}