package com.example.noteapp.data.repo

import com.example.noteapp.data.datasource.NoteDao
import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(private val dao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> = dao.getNotes()
    override suspend fun getNoteById(noteId: Int): Note= dao.getNoteById(noteId)
    override suspend fun insertNote(note: Note) = dao.insertNote(note)
    override suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}