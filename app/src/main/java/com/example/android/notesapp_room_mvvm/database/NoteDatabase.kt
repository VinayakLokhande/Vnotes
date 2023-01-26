package com.example.android.notesapp_room_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.notesapp_room_mvvm.models.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}