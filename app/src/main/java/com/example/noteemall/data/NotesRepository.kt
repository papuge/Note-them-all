package com.example.noteemall.data

import androidx.lifecycle.LiveData

class NotesRepository(
    private val noteDataDao: NoteDataDao,
    private val noteTagDao: NoteTagDao
) {
    val allNotes: LiveData<List<Note>> = noteDataDao.getAllNotesByTitle()

    suspend fun insertNote(note: Note) {
        val noteId = noteDataDao.insertNote(note)
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
