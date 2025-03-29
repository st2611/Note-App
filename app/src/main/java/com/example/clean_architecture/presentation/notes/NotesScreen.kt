package com.example.clean_architecture.presentation.notes


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.clean_architecture.presentation.navigation.Screen
import com.example.clean_architecture.presentation.navigation.UpdateNoteArgument
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navController: NavController,
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    val notes = notesViewModel.notesState.value

    val snackBarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddNoteScreen.route)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Note"
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier.fillMaxWidth().clickable {
                        navController.navigate(
                            Screen.UpdateNoteScreen.setParam(
                                UpdateNoteArgument(noteId = note.id)
                            )
                        )
                    }
                    ,
                    onDelete = {
                        notesViewModel.onEvent(NoteEvent.DeleteNote(note))
                        scope.launch {
                            val result = snackBarHostState.showSnackbar(
                                "Note Deleted",
                                "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                notesViewModel.onEvent(NoteEvent.RestoreNote)
                            }
                        }
                    }
                )
            }
        }
    }
}