package com.example.noteemall.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tags",
    indices = [Index(value = ["tag"], unique = true)]
    )
data class Tag (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    var id: Long,
    @ColumnInfo(name = "tag") var tag: String
)