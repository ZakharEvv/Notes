package com.example.notesnew.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesnew.Model.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotes() : LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    fun remove(id : Int)
}