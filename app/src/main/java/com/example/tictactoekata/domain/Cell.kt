package com.example.tictactoekata.domain

data class Cell(val player : Player? = null ) {
    fun isEmpty(): Boolean = player == null
}