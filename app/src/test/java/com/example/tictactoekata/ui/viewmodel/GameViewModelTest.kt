package com.example.tictactoekata.ui.viewmodel

import app.cash.turbine.test
import com.example.tictactoekata.domain.Board
import com.example.tictactoekata.domain.GameEvaluator
import com.example.tictactoekata.domain.Player
import com.example.tictactoekata.ui.TicTacToeState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GameViewModelTest {
    private val mockEvaluator = mockk<GameEvaluator>(relaxed = true)
    private lateinit var viewModel: GameViewModel

    @BeforeEach
    fun setup() {
        viewModel = GameViewModel(mockEvaluator)
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
            assertEquals(expectedGameState, awaitItem())
        }
    }

    @Test
    fun `valid play updates board and switches turn`() = runTest {
        every { mockEvaluator.calculateWinner(any()) } returns null
        every { mockEvaluator.isDraw(any()) } returns false

        viewModel.gameState.test {
            skipItems(1)

            viewModel.onCellSelected(0)

            val expectedState = TicTacToeState(
                board = Board().play(0, Player.X),
                currentPlayer = Player.O,
                winner = null,
                isDraw = false,
                errorMessage = null
            )
            assertEquals(expectedState, awaitItem())
        }
    }

    @Test
    fun `selecting occupied cells doesn't change state`() = runTest {
        every { mockEvaluator.calculateWinner(any()) } returns null
        every { mockEvaluator.isDraw(any()) } returns false

        viewModel.gameState.test {
            awaitItem()

            viewModel.onCellSelected(0)
            val gameStateAfterPlay = awaitItem()

            assertEquals(null, gameStateAfterPlay.errorMessage)

            viewModel.onCellSelected(0)
            val errorState = awaitItem()

            assertEquals("Cell 0 is already occupied.", errorState.errorMessage)
            assertEquals(Player.X, errorState.board.get(0).player)
        }
    }

    @Test
    fun `detects X win and stops game`() = runTest {
        every { mockEvaluator.calculateWinner(any()) } returns Player.X

        viewModel.gameState.test {
            awaitItem()

            viewModel.onCellSelected(0)

            val winState = awaitItem()

            assertEquals(Player.X, winState.winner)
            assertEquals(true, winState.isGameOver)

        }

    }

    @Test
    fun `detects O win and stops game`() = runTest {
        every { mockEvaluator.calculateWinner(any()) } returns Player.O

        viewModel.gameState.test {
            awaitItem()

            viewModel.onCellSelected(0)

            val winState = awaitItem()

            assertEquals(Player.O, winState.winner)
            assertEquals(true, winState.isGameOver)
        }
    }

    @Test
    fun `detects draw`() = runTest {
        every { mockEvaluator.calculateWinner(any()) } returns null
        every { mockEvaluator.isDraw(any()) } returns true

        viewModel.gameState.test {
            awaitItem()
            viewModel.onCellSelected(8)
            val state = awaitItem()
            assertEquals(true, state.isDraw)
            assertNull(state.winner)
        }
    }

    @Test
    fun `can not move after game is won`() = runTest {
        every { mockEvaluator.calculateWinner(any()) } returns Player.X
        viewModel.gameState.test {
            awaitItem()

            viewModel.onCellSelected(0)
            awaitItem()

            viewModel.onCellSelected(1)
            expectNoEvents()
        }

    }
}