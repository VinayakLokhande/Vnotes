package com.example.android.notesapp_room_mvvm.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.android.notesapp_room_mvvm.models.NoteModel

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : NoteModel)

    @Update
    suspend fun update(note : NoteModel)

    @Delete
    suspend fun delete(note : NoteModel)

    @Query("SELECT * FROM notesTable order by id ASC")
    fun getAllNotes() : LiveData<List<NoteModel>>

}