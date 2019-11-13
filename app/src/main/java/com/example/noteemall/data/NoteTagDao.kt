package com.example.noteemall.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteTagDao {

    @Insert
    suspend fun insertNoteTagJoin(noteTagJoin: NoteTagJoin): Long

    @Delete
    suspend fun deleteNoteTagJoin(noteTagJoin: NoteTagJoin)

    @Query("DELETE FROM note_tag_join")
    suspend fun deleteAllNoteTagJoins()

//    @Transaction @Query("SELECT * FROM notes")
//    suspend fun getAllNotesWithTags(): LiveData<List<NoteWithTagsPojo>>

    @Transaction @Query("SELECT * FROM notes WHERE note_id == :noteId")
    suspend fun getNoteWithTags(noteId: Long): NoteWithTagsPojo

//    @Transaction @Query("SELECT * FROM tags WHERE tag_id == :tagId")
//    suspend fun getTagWithNotes(tagId: Long): LiveData<List<TagWithNotesPojo>>

}