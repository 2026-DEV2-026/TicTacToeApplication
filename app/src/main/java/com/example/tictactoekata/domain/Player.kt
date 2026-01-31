package com.example.tictactoekata.domain

enum class Player(val playerName: String) {
    X("Player 1"),
    O("Player 2");

    fun next(): Player = if (this == X) O else X
}