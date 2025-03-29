package com.example.clean_architecture.domain.use_cases

import com.example.clean_architecture.domain.repository.NoteRepository

class GetNotes (
    private val noteRepository: NoteRepository
) {

    operator fun invoke() = noteRepository.getNotes()

}