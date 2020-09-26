package com.example.mynotipluse

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mynotipluse.di.factory.ViewModelInjector
import com.example.mynotipluse.room.NoteDao
import com.example.mynotipluse.room.NoteDatabase
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelInjector


lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        noteViewModel = ViewModelProvider(this,factory)[NoteViewModel::class.java]

        noteViewModel.loadNote()



        noteViewModel.allNoteResult().observe(this ,
        Observer {
            Toast.makeText(this,it.size.toString(),Toast.LENGTH_LONG).show()

        }
            )

        noteViewModel.allNoteError().observe(this ,
            Observer {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()

            }
        )

        noteViewModel.allNoteResult().observe(this ,
            Observer {
                Toast.makeText(this,it.size.toString(),Toast.LENGTH_LONG).show()

            }
        )

    }
}