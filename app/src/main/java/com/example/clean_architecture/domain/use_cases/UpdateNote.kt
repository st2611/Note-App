package com.example.clean_architecture.domain.use_cases

import com.example.clean_architecture.domain.model_entities.Note
import com.example.clean_architecture.domain.repository.NoteRepository

class UpdateNote (
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note) = noteRepository.updateNote(note)


}