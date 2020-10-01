package com.example.mynotipluse.di.module

import android.content.Context
import androidx.room.Room

import com.example.mynotipluse.room.NoteDao
import com.example.mynotipluse.room.NoteDatabase
import com.example.mynotipluse.room.NoteRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    @Singleton
    fun provideNoteDatabase(context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
            .build()
    }


    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase): NoteDao = database.nodDao()


    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {

        return NoteRepository(noteDao)
    }

}
