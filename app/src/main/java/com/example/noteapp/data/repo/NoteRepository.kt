package com.example.noteapp.data.repo

import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(noteId: Int): Note

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}