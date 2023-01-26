package com.example.android.notesapp_room_mvvm.database

import androidx.lifecycle.LiveData
import com.example.android.notesapp_room_mvvm.models.NoteModel

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes : LiveData<List<NoteModel>> = noteDao.getAllNotes()

    suspend fun insert(note : NoteModel) {
        noteDao.insert(note)
    }

    suspend fun update(note : NoteModel) {
        noteDao.update(note)
    }

    suspend fun delete(note : NoteModel) {
        noteDao.delete(note)
    }

}