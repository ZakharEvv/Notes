package com.example.notesnew.Database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesnew.Model.Note

@androidx.room.Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun notesDao() : NotesDao

    companion object{

        private var instance : NoteDatabase? = null

        private const val DB_NAME = "notes.db"

        fun getInstance(application: Application) : NoteDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(
                    application, NoteDatabase::class.java, DB_NAME
                ).build()
            return instance as NoteDatabase
        }
    }
}