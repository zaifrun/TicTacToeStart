package com.example.tictactoestart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class GameState {


    var fields = mutableStateListOf<FELT>().apply {
        addAll(List(9) { FELT.KRYDS })
    }



    fun resetState()
    {
        for (i in 0..8)
        {
            fields[i] = FELT.TOMT
        }
    }
}

enum class FELT {TOMT,KRYDS,BOLLE}