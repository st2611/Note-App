package com.example.clean_architecture.domain.use_cases

import com.example.clean_architecture.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int) = noteRepository.getNote(id)
}