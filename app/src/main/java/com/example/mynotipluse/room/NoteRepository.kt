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



}