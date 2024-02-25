package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.datasource.NoteDatabase
import com.example.noteapp.data.repo.NoteRepoImpl
import com.example.noteapp.data.repo.NoteRepository
import com.example.noteapp.domain.usecase.AddNote
import com.example.noteapp.domain.usecase.DeleteNote
import com.example.noteapp.domain.usecase.GetNoteById
import com.example.noteapp.domain.usecase.GetNotes
import com.example.noteapp.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepoImpl(db.noteDao())
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNoteById = GetNoteById(repository)
        )
    }
}
