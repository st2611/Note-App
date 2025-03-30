package com.example.clean_architecture.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.domain.model_entities.Note
import com.example.clean_architecture.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _notesState = mutableStateOf(emptyList<Note>())
    val notesState: State<List<Note>> = _notesState

    private var job: Job? = null
    private var deletedNote: Note? = null

    init {
        getNotes()
    }

    fun onEvent(event: NoteEvent) {
        if (event is NoteEvent.DeleteNote) {
            viewModelScope.launch {
                noteUseCases.deleteNote(event.note)
            }
            deletedNote = event.note
        } else {
            viewModelScope.launch {
                noteUseCases.addNote(deletedNote!!)
            }
        }
    }

    private fun getNotes() {
        job?.cancel()

        job = noteUseCases.getNotes().onEach {
            _notesState.value = it
        }.launchIn(viewModelScope)
    }
}