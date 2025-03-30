package com.example.clean_architecture.domain.use_cases

data class NoteUseCases(
    val addNote: AddNote,
    val updateNote: UpdateNote,
    val deleteNote: DeleteNote,
    val getNotes: GetNotes,
    val getNote: GetNote
)

