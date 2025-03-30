package com.example.clean_architecture.framework_driver.data.repository

import com.example.clean_architecture.domain.model_entities.Note
import com.example.clean_architecture.domain.repository.NoteRepository
import com.example.clean_architecture.framework_driver.data.data_source.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun addNote(note: Note) = noteDao.addNote(note)

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    override suspend fun getNote(id: Int) = noteDao.getNote(id)
}