package com.example.tictactoekata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tictactoekata.ui.TicTacToeScreen
import com.example.tictactoekata.ui.theme.TicTacToeKataTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(
            android.graphics.Color.TRANSPARENT
        ))
        setContent {
            TicTacToeKataTheme {
                TicTacToeScreen()
            }
        }
    }
}