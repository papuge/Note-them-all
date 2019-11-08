package com.example.noteemall.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "date") var creationDate: Date
)