package com.example.clean_architecture.framework_driver.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clean_architecture.domain.model_entities.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "note_db"
    }
}