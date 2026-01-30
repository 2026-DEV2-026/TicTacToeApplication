package com.example.tictactoekata.domain

enum class Player {
    X, O;

    fun next(): Player = O
}