package com.example.noteemall.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "date") var creationDate: LocalDateTime
): Parcelable