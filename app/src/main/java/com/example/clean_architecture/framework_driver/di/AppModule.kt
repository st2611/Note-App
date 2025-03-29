package com.example.clean_architecture.framework_driver.di

import android.app.Application
import androidx.room.Room
import com.example.clean_architecture.framework_driver.data.data_source.NoteDatabase
import com.example.clean_architecture.framework_driver.data.repository.NoteRepositoryImpl
import com.example.clean_architecture.domain.repository.NoteRepository
import com.example.clean_architecture.domain.use_cases.AddNote
import com.example.clean_architecture.domain.use_cases.DeleteNote
import com.example.clean_architecture.domain.use_cases.GetNote
import com.example.clean_architecture.domain.use_cases.GetNotes
import com.example.clean_architecture.domain.use_cases.NoteUseCases
import com.example.clean_architecture.domain.use_cases.UpdateNote
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
    fun provideDatabase(application: Application): NoteDatabase = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        NoteDatabase.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository = NoteRepositoryImpl(
        noteDao = noteDatabase.noteDao
    )

    @Provides
    @Singleton
    fun provideNoteUses(noteRepository: NoteRepository): NoteUseCases = NoteUseCases(
        addNote = AddNote(noteRepository),
        updateNote = UpdateNote(noteRepository),
        deleteNote = DeleteNote(noteRepository),
        getNotes = GetNotes(noteRepository),
        getNote = GetNote(noteRepository)
    )
}