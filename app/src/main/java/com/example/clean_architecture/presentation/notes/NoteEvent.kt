package com.example.clean_architecture.presentation.notes

import com.example.clean_architecture.domain.model_entities.Note

sealed class NoteEvent {
    data class DeleteNote(val note: Note) : NoteEvent()
    data object RestoreNote : NoteEvent()
}