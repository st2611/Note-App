package com.example.clean_architecture.presentation.update_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcomentry.lib.from
import com.example.clean_architecture.domain.model_entities.Note
import com.example.clean_architecture.domain.use_cases.NoteUseCases
import com.example.clean_architecture.presentation.navigation.UpdateNoteArgument
import com.example.clean_architecture.presentation.util.AddEditNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedSateHandle: SavedStateHandle
): ViewModel() {

    init {
        val argument = UpdateNoteArgument().from(savedSateHandle)

        viewModelScope.launch {
            noteUseCases.getNote(argument.noteId)?.let {
                _title.value = it.title
                _content.value = it.content
                currentNodeId = it.id
            }
        }
    }

    private val _title = mutableStateOf(" ")
    val title: State<String> = _title

    private val _content = mutableStateOf(" ")
    val content: State<String> = _content

    private var currentNodeId: Int? = null

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _title.value = event.title
            }
            is AddEditNoteEvent.EnteredContent -> {
                _content.value = event.content
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    noteUseCases.updateNote(
                        Note(
                            title = title.value,
                            content = content.value,
                            timestamp = System.currentTimeMillis()
                        ).apply {
                            currentNodeId?.let {
                                id = it
                            }
                        }
                    )
                }
            }
        }
    }
}