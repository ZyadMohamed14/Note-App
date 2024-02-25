package com.example.noteapp.domain.usecase

data class NoteUseCases(val getNotes: GetNotes, val deleteNote: DeleteNote, val addNote: AddNote, val getNoteById: GetNoteById)
