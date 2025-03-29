package com.example.clean_architecture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devcomentry.lib.composable
import com.example.clean_architecture.presentation.add_note.AddNoteScreen
import com.example.clean_architecture.presentation.notes.NotesScreen
import com.example.clean_architecture.presentation.update_note.UpdateNoteScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(
            screen = Screen.MainScreen
        ) {
            NotesScreen(navController)
        }

        composable(
            screen = Screen.AddNoteScreen
        ) {
            AddNoteScreen(navController)
        }

        composable(
            screen = Screen.UpdateNoteScreen,
            arguments = UpdateNoteArgument()
        ) {
            UpdateNoteScreen(navController)
        }
    }
}