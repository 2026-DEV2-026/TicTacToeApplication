package com.example.tictactoekata.domain

enum class Player {
    X, O;

    fun next(): Player = if (this == X) O else X
}