package com.example.tictactoekata.domain

data class Board(
    val cells: List<Cell> = List(9) { Cell() }
) {
    fun get(index: Int): Cell = cells[index]
    fun play(index: Int, player: Player): Board {
        val newCells = cells.toMutableList()
        newCells[index] = Cell(player)
        return copy(cells = newCells)
    }
}