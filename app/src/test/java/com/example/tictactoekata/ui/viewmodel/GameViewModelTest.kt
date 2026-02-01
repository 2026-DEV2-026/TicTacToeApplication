package com.example.tictactoekata.ui.viewmodel

import app.cash.turbine.test
import com.example.tictactoekata.domain.Board
import com.example.tictactoekata.domain.Player
import com.example.tictactoekata.ui.TicTacToeState
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class GameViewModelTest {
    private lateinit var viewModel: GameViewModel

    @BeforeEach
    fun setup() {
        viewModel = GameViewModel()
    }

    @Test
    fun `initial state is valid`() = runTest {
        viewModel.gameState.test {
            val expectedGameState = TicTacToeState(
                currentPlayer = Player.X,
                winner = null,
                isDraw = false,
                board = Board(),
                errorMessage = null
            )
            assertEquals(expectedGameState,awaitItem())
        }
    }

}