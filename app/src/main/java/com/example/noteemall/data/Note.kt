package com.example.noteemall.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "notes", indices = [Index("note_id")])
@Parcelize
data class Note(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String? = "",
    @ColumnInfo(name = "creation_date")
    var creationDate: Date = Date(),
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var id: Long = 0
): Parcelable