package com.example.noteapp.domain.usecase

import com.example.noteapp.data.repo.NoteRepository
import com.example.noteapp.domain.model.Note

class DeleteNote(private val repository: NoteRepository) {
    suspend operator fun invoke (note: Note){
        repository.deleteNote(note)
    }
}