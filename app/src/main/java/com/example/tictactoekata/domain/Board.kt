package com.example.tictactoekata.domain

class Board {
    val cells: List<Cell> = List(9) { Cell() }

    fun get(index: Int): Cell = cells[index]
}