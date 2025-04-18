package com.example.clean_architecture.domain.repository

import com.example.clean_architecture.domain.model_entities.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNote(id: Int): Note?
}