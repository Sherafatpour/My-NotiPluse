package net.sherafatpour.mynotepluse.di.module

import android.content.Context
import androidx.room.Room

import net.sherafatpour.mynotepluse.room.NoteDao
import net.sherafatpour.mynotepluse.room.NoteDatabase
import net.sherafatpour.mynotepluse.room.NoteRepository
import dagger.Module
import dagger.Provides
import net.sherafatpour.mynotepluse.NoteViewModel
import net.sherafatpour.mynotepluse.di.NoteAdapter

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


    @Provides
    @Singleton
    fun provideNoteViewModel(repository: NoteRepository):NoteViewModel{
        return NoteViewModel(repository)
    }

    @Provides
    @Singleton
    fun provideNoteAdapter(context: Context):NoteAdapter{
        return NoteAdapter(context)
    }

}
