package com.example.clean_architecture.framework_driver.di

import android.app.Application
import androidx.room.Room
import com.example.clean_architecture.domain.repository.NoteRepository
import com.example.clean_architecture.domain.use_cases.AddNote
import com.example.clean_architecture.domain.use_cases.DeleteNote
import com.example.clean_architecture.domain.use_cases.GetNote
import com.example.clean_architecture.domain.use_cases.GetNotes
import com.example.clean_architecture.domain.use_cases.NoteUseCases
import com.example.clean_architecture.domain.use_cases.UpdateNote
import com.example.clean_architecture.framework_driver.data.data_source.NoteDatabase
import com.example.clean_architecture.framework_driver.data.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): NoteDatabase = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        NoteDatabase.DB_NAME
    ).fallbackToDestructiveMigration().build()
    //Mặc định, nếu bạn thay đổi schema (bổ sung cột mới, đổi kiểu dữ liệu,...)
    // mà không khai báo Migration, Room sẽ bị crash.
    //Dùng cách này thì Room sẽ xóa sạch dữ liệu khi schema thay đổi.
    // Nếu bạn muốn giữ dữ liệu, cần tự viết Migration.

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository = NoteRepositoryImpl(
        noteDao = noteDatabase.noteDao
    )

    @Provides
    @Singleton
    fun provideNoteUses(noteRepository: NoteRepository): NoteUseCases = NoteUseCases(
        addNote = AddNote(noteRepository),
        updateNote = UpdateNote(noteRepository),
        deleteNote = DeleteNote(noteRepository),
        getNotes = GetNotes(noteRepository),
        getNote = GetNote(noteRepository)
    )
}