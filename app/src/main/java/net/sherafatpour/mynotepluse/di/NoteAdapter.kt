package net.sherafatpour.mynotepluse.di

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotipluse.R
import kotlinx.android.synthetic.main.note_item.view.*
import net.sherafatpour.mynotepluse.room.Note
import javax.inject.Inject

class NoteAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    private var notes: List<Note> = ArrayList()
    lateinit var listener: OnItemClickListener

    fun addNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])

    }

    fun getNoteAt(position:Int):Note{

        return notes[position]
    }
    override fun getItemCount() = notes.size


    inner class NoteViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {

        private var noteTitle: TextView? = null
        private var noteDescription: TextView? = null
        private var notePriority: TextView? = null

        init {
            noteTitle = itemView.noteTitle
            noteDescription = itemView.noteDescription
            notePriority = itemView.notePriority
            itemView.setOnClickListener{
                val position=layoutPosition
                if (position != RecyclerView.NO_POSITION )
                listener.onItemClick( notes[position])

            }
        }

        fun bind(note: Note) {
            noteTitle!!.text = note.title
            noteDescription!!.text = note.description
            notePriority!!.text = note.priority.toString()
        }




    }

    interface OnItemClickListener {

        fun onItemClick(note: Note)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {

        this.listener = listener
    }

}