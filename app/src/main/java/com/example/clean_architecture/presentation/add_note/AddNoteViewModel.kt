package com.example.clean_architecture.presentation.add_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.domain.model_entities.Note
import com.example.clean_architecture.domain.use_cases.NoteUseCases
import com.example.clean_architecture.presentation.util.AddEditNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel() {
    private val _title = mutableStateOf(" ")
    val title: State<String> = _title

    private val _content = mutableStateOf(" ")
    val content: State<String> = _content

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
                    noteUseCases.addNote(
                        Note(
                            title = title.value,
                            content = content.value,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }
        }
    }
}