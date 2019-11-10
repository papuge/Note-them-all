package com.example.noteemall.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteTagDao {

    @Insert
    suspend fun insertNoteTagJoin(noteTagJoin: NoteTagJoin): Long

    @Query("SELECT * FROM notes")
    fun getAllNotesWithTags(): LiveData<List<NoteWithTagsPojo>>

    @Query("SELECT * FROM notes WHERE note_id == :noteId")
    fun getNoteWithTags(noteId: Long): LiveData<List<NoteWithTagsPojo>>

    @Query("SELECT * FROM tags WHERE tag_id == :tagId")
    fun getTagWithNotes(tagId: Long): LiveData<List<TagWithNotesPojo>>

}