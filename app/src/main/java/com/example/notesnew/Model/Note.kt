package com.example.notesnew.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(tableName = "notes")
class Note(name : String, text : String, date : String) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var name : String
    var text : String
    var date : String

    init {
        this.name = name
        this.text = text
        this.date = date
    }
}