package com.example.android.notesapp_room_mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.notesapp_room_mvvm.database.NoteDatabase
import com.example.android.notesapp_room_mvvm.database.NoteRepository
import com.example.android.notesapp_room_mvvm.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<NoteModel>>
    val repository : NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }


    fun deleteNote(note : NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note : NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun insertNote(note : NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}