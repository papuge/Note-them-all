package com.example.noteemall.data

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.runBlocking

class NotesRepository(
    private val noteDataDao: NoteDataDao,
    private val noteTagDao: NoteTagDao
) {
    val allNotes: LiveData<List<Note>> = noteDataDao.getAllNotesByTitle()

    suspend fun insertNote(note: Note, tags: List<Tag>) {
        val noteId = noteDataDao.insertNote(note)
        Log.d("Repository", "inserted noteId ${noteId}")
        if (tags.isNotEmpty()) {
            val tagsIds = mutableListOf<Long>()
            for (tag in tags) {
                val existTagId = noteDataDao.getTagId(tag.tag)
                if (existTagId == null) {
                    tagsIds.add(noteDataDao.insertTag(tag))
                } else {
                    tagsIds.add(existTagId)
                }
                Log.d("Repository", "inserted tag ${tag.tag}")
            }
            for (tagId in tagsIds) {
                val id = noteTagDao.insertNoteTagJoin(NoteTagJoin(noteId, tagId))
                Log.d("Repository", "inserted join $id")
            }
        }
    }

    fun getTagsFromNote(noteId: Long): List<Tag>  = runBlocking {
        val notePojo: NoteWithTagsPojo = noteTagDao.getNoteWithTags(noteId)
        Log.d("Repository", "requested noteId ${noteId}")
        Log.d("Repository", "Gettin tags ${notePojo.tags}")
        return@runBlocking when {
            notePojo.tags.isNotEmpty() -> notePojo.tags
            else -> listOf()
        }
    }

    suspend fun updateNote(note: Note) {
        noteDataDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDataDao.deleteNote(note)
    }

    suspend fun insertTags(vararg tags: Tag) {
        noteDataDao.insertTags(*tags)
    }

    suspend fun deleteTag(tag: Tag) {
        noteDataDao.deleteTag(tag)
    }

}
