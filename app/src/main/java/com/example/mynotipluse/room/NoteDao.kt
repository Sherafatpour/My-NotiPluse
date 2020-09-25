package com.example.mynotipluse.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Observable

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM NOTE_TABLE")
    fun deleteAllNote()

   /* @get:Query("SELECT * FROM note_table ORDER BY priority DESC")
    var getAllNote:LiveData<List<Note>>
*/
   @Query("SELECT * FROM note_table ORDER BY priority DESC")
   fun getAllNotes(): Observable<List<Note>>

}