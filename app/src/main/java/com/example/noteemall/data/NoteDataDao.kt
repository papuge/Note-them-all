package com.example.noteemall.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDataDao {
    @Insert
    fun insertNote(note: Note): Long

    @Insert
    fun insertNotes(vararg notes: Note): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTag(tag: Tag): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTags(vararg tags: Tag): List<Long>

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Delete
    fun deleteTag(tag: Tag)

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getAllNotesByTitle(): LiveData<List<Note>>

    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAllNotesByDate(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE note_id == :noteId LIMIT 1")
    fun getNoteById(noteId: Long): Note

    @Query("SELECT * FROM tags WHERE tag_id == :tagId LIMIT 1")
    fun getTagById(tagId: Long): Tag

    @Query("SELECT * FROM tags")
    fun getAllTags(): List<Tag>

}