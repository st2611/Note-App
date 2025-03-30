package com.example.clean_architecture.presentation.navigation

import com.devcomentry.lib.ComposeScreen

sealed class Screen(_route: String) : ComposeScreen(_route) {
    data object MainScreen : Screen("main_screen")
    data object AddNoteScreen : Screen("add_note_screen")
    data object UpdateNoteScreen : Screen("update_note_screen")
}
//sealed class: chỉ có các lớp con được khai báo bên trong cùng một file mới có thể kế thừa nó.
//data object (Kotlin 1.9+): Đây là một dạng đặc biệt của object nhưng có thêm các tính năng của data class , giúp nó tự động hỗ trợ equals() và toString().
