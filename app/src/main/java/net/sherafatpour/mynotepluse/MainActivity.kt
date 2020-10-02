package net.sherafatpour.mynotepluse

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotipluse.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.sherafatpour.mynotepluse.di.NoteAdapter
import net.sherafatpour.mynotepluse.di.factory.ViewModelInjector
import net.sherafatpour.mynotepluse.room.Note
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    companion object {

        val ADD_NOTE_REQUEST = 1
        val EDIT_NOTE_REQUEST = 2

    }

    @Inject
    lateinit var factory: ViewModelInjector

    @Inject
    lateinit var noteAdapter: NoteAdapter


    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        recyclerviewInit()
        getAllNotes()

        gotToAddNoteActivity.setOnClickListener {

            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        noteAdapter.setOnItemClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                intent.putExtra(AddNoteActivity.EXTRA_ID, note.id)
                intent.putExtra(AddNoteActivity.EXTRA_TITLE, note.title)
                intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, note.description)
                intent.putExtra(AddNoteActivity.EXTRA_PRIORITY, note.priority)
                startActivityForResult(intent, EDIT_NOTE_REQUEST)
            }

        })

    }



    private fun getAllNotes() {
        noteViewModel.getAllNotes().observe(this,
            Observer {
                if (it.isEmpty()){

                    nothing.visibility = View.VISIBLE
                    text.visibility = View.VISIBLE
                }else {
                    nothing.visibility = View.GONE
                    text.visibility = View.GONE

                }
                noteAdapter.addNotes(it)
            }
        )

        noteViewModel.allNoteError().observe(this,
            Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()

            }
        )
    }


    private fun recyclerviewInit() {
        note_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        note_recyclerview.adapter = noteAdapter
        note_recyclerview.setHasFixedSize(false)
        ItemTouchHelper( object : ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                Toast.makeText(this@MainActivity,"Note Removed",Toast.LENGTH_LONG).show()
                noteViewModel.deleteNote(noteAdapter.getNoteAt(viewHolder.adapterPosition))

            }

        }).attachToRecyclerView(note_recyclerview)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {

            val title = data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1)
            val note = Note(title!!, description!!, priority)

            noteViewModel.insertNote(note)

            Toast.makeText(this@MainActivity, "Note saved", Toast.LENGTH_SHORT).show()

        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {

            val id: Int = data!!.getIntExtra(AddNoteActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Note can't be update", Toast.LENGTH_SHORT).show()
                return
            }

            val title: String = data.getStringExtra(AddNoteActivity.EXTRA_TITLE)!!
            val description: String =
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)!!
            val priority: Int = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1)
            val note = Note(title, description, priority)
            note.id = id
            noteViewModel.updateNote(note = note)

            Toast.makeText(this, "Note Updated$description", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this@MainActivity, "Note not saved", Toast.LENGTH_SHORT).show()

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.deleteAllNote -> {

                deleteAllNote()
                true
            }
            else -> {

                super.onOptionsItemSelected(item)

            }
        }
    }

    private fun deleteAllNote() {
        noteViewModel.deleteAllNotes()
    }

}