package com.example.tictactoekata.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoekata.domain.GameEvaluator
import com.example.tictactoekata.ui.TicTacToeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val evaluator: GameEvaluator
) : ViewModel() {
    private val _gameState = MutableStateFlow(TicTacToeState())
    val gameState: StateFlow<TicTacToeState> = _gameState.asStateFlow()

    fun onCellSelected(index: Int) {
        val currentState = _gameState.value
        runCatching {
            val newBoard = currentState.board.play(index, currentState.currentPlayer)

            _gameState.value = currentState.copy(
                board = newBoard,
                currentPlayer = currentState.currentPlayer.next(),
                errorMessage = null
            )
        }.onFailure { error ->
            _gameState.value = currentState.copy(errorMessage = error.message)
        }

    }
}