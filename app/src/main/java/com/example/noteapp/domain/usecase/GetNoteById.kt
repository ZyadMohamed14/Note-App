package com.example.noteapp.domain.usecase

import com.example.noteapp.data.repo.NoteRepository
import com.example.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

class GetNoteById (private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id:Int): Note {
        return noteRepository.getNoteById(id)
    }
}