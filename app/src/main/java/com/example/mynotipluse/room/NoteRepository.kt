package com.example.mynotipluse.room

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.*
import javax.inject.Inject

class NoteRepository @Inject constructor(val noteDao: NoteDao) {

    fun getAllNotes():Observable<List<Note>>{
        return noteDao.getAllNotes().doOnNext {
            Log.e("REPOSITORY DB *** ", it.size.toString())

        }
    }

    fun insertNote(note: Note){
        noteDao.insertNote(note = note)
    }
    fun updateNote(note: Note){
        noteDao.updateNote(note = note)
    }
    fun deleteNote(note: Note){
        noteDao.deleteNote(note = note)
    }
    fun deleteAllNote(note: Note){
        noteDao.deleteAllNote()
    }
}