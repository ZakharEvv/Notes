package com.example.notesnew.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesnew.Database.NoteDatabase
import com.example.notesnew.Model.Note

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var noteDatabase : NoteDatabase

    init {
        noteDatabase = NoteDatabase.getInstance(application)
    }

    fun getNotes() : LiveData<List<Note>>{
        return noteDatabase.notesDao().getNotes()
    }

    fun remove(note : Note) {
        var t = Thread{
            noteDatabase.notesDao().remove(note.id)
        }.start()
    }

    fun saveNote(note : Note){
        var t = Thread{
            noteDatabase.notesDao().add(note)
        }.start()
    }
}