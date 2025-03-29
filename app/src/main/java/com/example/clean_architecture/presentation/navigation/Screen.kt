package com.example.clean_architecture.presentation.navigation

import com.devcomentry.lib.ComposeScreen

sealed class Screen(_route: String): ComposeScreen(_route){
    data object MainScreen: Screen("main_screen")
    data object AddNoteScreen: Screen("add_note_screen")
    data object UpdateNoteScreen: Screen("update_note_screen")
}