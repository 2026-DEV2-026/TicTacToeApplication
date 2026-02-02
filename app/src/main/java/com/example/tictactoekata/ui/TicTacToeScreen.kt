package com.example.tictactoekata.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tictactoekata.domain.BOARD_SIZE
import com.example.tictactoekata.domain.Board
import com.example.tictactoekata.domain.Cell
import com.example.tictactoekata.domain.Player
import com.example.tictactoekata.domain.TOTAL_CELLS
import com.example.tictactoekata.ui.theme.TicTacToeKataTheme
import com.example.tictactoekata.ui.viewmodel.GameViewModel

private object SpacingForScreen {
    val TitleBottom = 32.dp
    val BoardBottom = 24.dp
}
private object BoardDimens {
    val Size = 300.dp
    val BorderWidth = 2.dp
    val CellSize = 100.dp
    val CellBorderWidth = 1.dp
}

private object GameColors {
    val BoardBorder = Color.Black
    val CellBorder = Color.LightGray
    val PlayerX = Color.Blue
    val PlayerO = Color.Red
}

private object TextDimens {
    val PlayerSymbol = 40.sp
}

@Composable
fun TicTacToeScreen(viewModel: GameViewModel = hiltViewModel()) {

    val state by viewModel.gameState.collectAsStateWithLifecycle()

    TicTacToeContent(
    state = state,
    onCellSelected = viewModel::onCellSelected,
    onResetGame = viewModel::onResetGame
    )
}
@Composable
fun TicTacToeContent(
    state: TicTacToeState,
    onCellSelected: (Int) -> Unit,
    onResetGame: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = when {
                state.winner != null -> "Winner: ${state.winner.playerName}"
                state.isDraw -> "It's a Draw!"
                else -> "${state.currentPlayer.playerName}'s Turn"
            },
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(SpacingForScreen.TitleBottom))
        LazyVerticalGrid(
            columns = GridCells.Fixed(
                BOARD_SIZE
            ),
            modifier = Modifier
                .size(BoardDimens.Size)
                .border(BoardDimens.BorderWidth, GameColors.BoardBorder)
        ) {
            val boardCells = state.board.cells
            items(TOTAL_CELLS) { index ->
                val cell = boardCells[index]
                Box(
                    modifier = Modifier
                        .size(BoardDimens.CellSize)
                        .border(BoardDimens.CellBorderWidth, GameColors.CellBorder)
                        .clickable(
                        enabled = state.isCellEnabled(index),
                onClick = { onCellSelected(index) }
                ),
                    contentAlignment = Alignment.Center
                ){
                    if (cell.player != null) {
                        Text(
                            text = cell.player.name,
                            fontSize = TextDimens.PlayerSymbol,
                            color = if (cell.player == Player.X) GameColors.PlayerX else GameColors.PlayerO
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isGameOver) {
                Spacer(modifier = Modifier.height(SpacingForScreen.BoardBottom))
                Button(onClick = onResetGame) {
                    Text("Play Again")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "New Game")
@Composable
fun PreviewNewGame(){
    TicTacToeKataTheme() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacToeContent(
                state = TicTacToeState(),
                onCellSelected = {},
                onResetGame = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "X Wins")
@Composable
fun PreviewWinnerX(){
    val winningCells = listOf(
        Cell(Player.X), Cell(Player.X), Cell(Player.X),
        Cell(Player.O),Cell(Player.O),Cell(null),
        Cell(null),Cell(null),Cell(null))

    val wonState = TicTacToeState(
        board = Board(winningCells),
        winner = Player.X,
        isGameOver = true,
        currentPlayer = Player.O ,
        isDraw = false
    )

    TicTacToeKataTheme{
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacToeContent(
                state = wonState,
                onCellSelected = {},
                onResetGame = {}
            )
        }
    }
}


@Preview(showBackground = true, name = "O Wins")
@Composable
fun PreviewWinnerO(){
    val winningCells = listOf(
        Cell(Player.O), Cell(Player.O), Cell(Player.O),
        Cell(Player.X),Cell(Player.X),Cell(null),
        Cell(null),Cell(null),Cell(null))

    val wonState = TicTacToeState(
        board = Board(winningCells),
        winner = Player.O,
        isGameOver = true,
        currentPlayer = Player.X ,
        isDraw = false
    )

    TicTacToeKataTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            color = MaterialTheme.colorScheme.background,
        ) {
            TicTacToeContent(
                state = wonState,
                onCellSelected = {},
                onResetGame = {}
            )
        }
    }
}
