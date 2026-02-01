package com.example.tictactoekata.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoekata.ui.TicTacToeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val _gameState = MutableStateFlow(TicTacToeState())
    val gameState: StateFlow<TicTacToeState> = _gameState.asStateFlow()
}