package com.example.tictactoekata.domain

const val BOARD_SIZE = 3
const val TOTAL_CELLS = BOARD_SIZE * BOARD_SIZE
const val MIN_CELL = 0
data class Board(
    val cells: List<Cell> = List(TOTAL_CELLS) { Cell() }
) {
    fun get(index: Int): Cell = cells[index]

    fun play(index: Int, player: Player): Board {
        require(index >= MIN_CELL) {
            "Index $index is below the minimum ($MIN_CELL)."
        }
        val newCells = cells.toMutableList()
        newCells[index] = Cell(player)
        return copy(cells = newCells)
    }
}