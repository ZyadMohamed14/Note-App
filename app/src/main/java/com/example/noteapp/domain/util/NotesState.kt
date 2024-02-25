package com.example.noteapp.domain.util

import com.example.noteapp.domain.model.Note

data class NotesState(
   val notes: List<Note> = emptyList() ,
    val noteOrder : NoteOrder =NoteOrder.Date(OrderType.Desending),
    val isOrderSectionVisible :Boolean = false
)

