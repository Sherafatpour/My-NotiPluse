package net.sherafatpour.mynotepluse

import android.app.Activity
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotipluse.R
import kotlinx.android.synthetic.main.content_add_note3.*


class AddNoteActivity : AppCompatActivity() {

    companion object {

        val EXTRA_ID = "name.sherafatpour.mynotepad.EXTRA_ID"
        val EXTRA_TITLE = "name.sherafatpour.mynotepad.EXTRA_TITLE"
        val EXTRA_DESCRIPTION = "name.sherafatpour.mynotepad.EXTRA_DESCRIPTION"
        val EXTRA_PRIORITY = "name.sherafatpour.mynotepad.EXTRA_PRIORITY"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note_)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
        np_priority.minValue = 1
        np_priority.maxValue = 10


        val intent = intent

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            edt_title.setText(intent.getStringExtra(EXTRA_TITLE))
            edt_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            np_priority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else {
            title = "Add Note"
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)


        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.saveNote -> {

                saveNote()
                true
            }
            else -> {

                super.onOptionsItemSelected(item)

            }
        }

    }

    private fun saveNote() {

        val title = edt_title.text.toString()
        val description = edt_description.text.toString()
        val priority = np_priority.value


        if (title.trim().isEmpty() || description.trim().isEmpty()) {

            Toast.makeText(
                this@AddNoteActivity,
                " Please insert a title and description",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)
        val id = intent.getIntExtra(EXTRA_ID, -1)

        if (id != -1){
            data.putExtra(EXTRA_ID, id)
        }
        setResult(Activity.RESULT_OK, data)
        finish()

    }

}