package com.example.noteapp.domain.usecase

import com.example.noteapp.data.repo.NoteRepository
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.util.NoteOrder
import com.example.noteapp.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val repo:NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Desending)):Flow<List<Note>> {
       return repo.getNotes().map { notes ->
           when(noteOrder.orderType){
               OrderType.Ascending -> {
                   when(noteOrder){
                       is NoteOrder.Color ->  notes.sortedBy { it.color }
                       is NoteOrder.Date ->  notes.sortedBy { it.timeStamp }
                       is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                   }
               }
               OrderType.Desending -> {
                   when(noteOrder){
                       is NoteOrder.Color ->  notes.sortedByDescending { it.color }
                       is NoteOrder.Date ->  notes.sortedByDescending { it.timeStamp }
                       is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                   }
               }
           }
       }
    }
}