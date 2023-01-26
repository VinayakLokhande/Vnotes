package com.example.android.notesapp_room_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.android.notesapp_room_mvvm.models.NoteModel
import com.example.android.notesapp_room_mvvm.viewmodel.NoteViewModel
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddUpdateNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEditTxt : TextInputEditText
    lateinit var noteBodyEditTxt : TextInputEditText
    lateinit var noteAddBtn : Button
    lateinit var viewModel : NoteViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_note)

        noteTitleEditTxt = findViewById(R.id.noteTitleEditTxt)
        noteBodyEditTxt = findViewById(R.id.noteBodyEditTxt)
        noteAddBtn = findViewById(R.id.addNoteBtn)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")
        if (noteType == "Edit") {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteBody = intent.getStringExtra("noteBody")
            noteId = intent.getIntExtra("noteId",-1)

            noteAddBtn.text = "Update Note"
            noteTitleEditTxt.setText(noteTitle)
            noteBodyEditTxt.setText(noteBody)
        } else {
            noteAddBtn.text = "Save Note"
        }


        noteAddBtn.setOnClickListener {
            val noteTitle = noteTitleEditTxt.text.toString()
            val noteBody = noteBodyEditTxt.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM, yyy - HH:mm")
                    val currentDate : String = simpleDateFormat.format(Date())

                    val updateNote = NoteModel(noteTitle,noteBody,currentDate)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,R.string.Note_updated,Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
                    val simpleDateFormat = SimpleDateFormat("dd MMM, yyy - HH:mm")
                    val currentDate : String = simpleDateFormat.format(Date())
                    viewModel.insertNote(NoteModel(noteTitle,noteBody,currentDate))
                    Toast.makeText(this,R.string.Note_added,Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(this@AddUpdateNoteActivity,MainActivity::class.java))
            this.finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }

}