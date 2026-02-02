package com.example.tictactoekata.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoekata.domain.GameEvaluator
import com.example.tictactoekata.ui.TicTacToeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val evaluator: GameEvaluator
) : ViewModel() {
    sealed interface GameAction {
        data class SelectCell(val index: Int) : GameAction
        data object ResetGame : GameAction
    }

    private val _actions = Channel<GameAction>(Channel.UNLIMITED)

    fun onCellSelected(index: Int) = _actions.trySend(GameAction.SelectCell(index))

    fun onResetGame() = _actions.trySend(GameAction.ResetGame)

    val gameState: StateFlow<TicTacToeState> = _actions.receiveAsFlow()
        .runningFold(TicTacToeState(), ::updateState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = TicTacToeState()
        )

    private fun selectCell(state: TicTacToeState, index: Int): TicTacToeState {
        if (state.isGameOver) return state

        return runCatching {
            val newBoard = state.board.play(index, state.currentPlayer)
            val winner = evaluator.calculateWinner(newBoard)
            val isDraw = evaluator.isDraw(newBoard)
            val isGameOver = winner != null || isDraw


            state.copy(
                board = newBoard,
                winner = winner,
                isDraw = isDraw,
                currentPlayer = if (isGameOver) state.currentPlayer else state.currentPlayer.next(),
                isGameOver = isGameOver,
                errorMessage = null,
            )
        }.getOrElse { error ->
            state.copy(errorMessage = error.message)
        }
    }

    private fun updateState(state: TicTacToeState, action: GameAction): TicTacToeState {
        return when (action) {
            is GameAction.ResetGame -> resetGame()
            is GameAction.SelectCell -> selectCell(state, action.index)
        }
    }

    private fun resetGame(): TicTacToeState {
        return TicTacToeState()
    }
}