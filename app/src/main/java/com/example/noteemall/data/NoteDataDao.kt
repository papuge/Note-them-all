package com.example.noteemall.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDataDao {
    @Insert
    suspend fun insertNote(note: Note): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTag(tag: Tag): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTags(vararg tags: Tag): List<Long>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Delete
    suspend fun deleteTag(tag: Tag)

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