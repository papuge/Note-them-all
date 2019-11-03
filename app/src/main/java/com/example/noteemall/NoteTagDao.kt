package com.example.noteemall

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface NoteTagDao {

    @Insert
    fun insertNoteTagJoin(personCarJoin: NoteTagJoin): Long

    @Query("SELECT * FROM notes")
    fun getAllNotesWithTags(): List<NoteWithTagsPojo>

    @Query("SELECT * FROM notes WHERE note_id == :noteId")
    fun getNoteWithTags(noteId: Long): List<NoteWithTagsPojo>

    @Query("SELECT * FROM tags WHERE tag_id == :tagId")
    fun getTagWithNotes(tagId: Long): List<NoteWithTagsPojo>

}