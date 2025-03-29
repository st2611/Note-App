package com.example.clean_architecture.presentation.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.clean_architecture.domain.model_entities.Note

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier,
    onDelete: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column (
            modifier = modifier.fillMaxSize().padding(8.dp).padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.displayMedium,
                color = Color.Black,
                maxLines = 1
            )

            Text(
                text = note.content,
                style = MaterialTheme.typography.displaySmall,
                color = Color.Black,
                maxLines = 5
            )
        }

        IconButton(
            modifier = Modifier,
            onClick = onDelete
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Note"
            )
        }
    }
}