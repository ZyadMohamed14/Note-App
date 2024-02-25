package com.example.noteapp.domain.util

import androidx.compose.ui.graphics.Color

sealed class OrderType {
    object Ascending:OrderType()
    object Desending :OrderType()

}