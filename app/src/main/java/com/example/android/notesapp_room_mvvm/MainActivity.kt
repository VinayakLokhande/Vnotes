 package com.example.android.notesapp_room_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.notesapp_room_mvvm.adapter.NoteAdapter
import com.example.android.notesapp_room_mvvm.adapter.OnClickDeleteNoteBtnInterface
import com.example.android.notesapp_room_mvvm.adapter.OnClickNoteInterface
import com.example.android.notesapp_room_mvvm.models.NoteModel
import com.example.android.notesapp_room_mvvm.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

 class MainActivity : AppCompatActivity(), OnClickNoteInterface, OnClickDeleteNoteBtnInterface {

    lateinit var noteRv : RecyclerView
    lateinit var floatBtn : FloatingActionButton
    lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRv = findViewById(R.id.noteRecyclerView)
        floatBtn = findViewById(R.id.FabBtn)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        val noteAdapter = NoteAdapter(this,this,this)
        noteRv.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        noteRv.adapter = noteAdapter

        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                noteAdapter.updateNoteList(it)
            }

        })

        floatBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,AddUpdateNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }


    }

     override fun onClickNote(note: NoteModel) {
         val intent = Intent(this@MainActivity,AddUpdateNoteActivity::class.java)
         intent.putExtra("noteType","Edit")
         intent.putExtra("noteId",note.id)
         intent.putExtra("noteTitle",note.noteTitle)
         intent.putExtra("noteBody",note.noteDescription)
         startActivity(intent)
         this.finish()
     }

     override fun onClickDeleteBtn(note: NoteModel) {
        viewModel.deleteNote(note)
         Toast.makeText(this, "${note.noteTitle} Deleted ",Toast.LENGTH_SHORT).show()
     }

 }