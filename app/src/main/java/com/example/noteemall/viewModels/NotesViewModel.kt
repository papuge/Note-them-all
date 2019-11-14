package com.example.noteemall.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteemall.data.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NotesRepository

    val allNotes: LiveData<List<Note>>
    val TAG = "NotesViewModel"

    init {
        Log.d(TAG, "DB was not created")
        val db = NotesDatabase.getDatabase(application, viewModelScope)
        Log.d(TAG, "DB was created")
        val noteDataDao = db.run { noteDataDao() }
        Log.d(TAG, "The first dao access")
        val noteTagDao = db.run { noteTagDao() }
        Log.d(TAG, "The second dao access")
        repository = NotesRepository(noteDataDao, noteTagDao)
        allNotes = repository.allNotes
    }

    fun insertNote(note: Note, tagsString: String) = viewModelScope.launch {
        val tagsList = separateTags(tagsString).map { tagStr -> Tag(tagStr) }
        repository.insertNote(note, tagsList)
    }

    fun fetchTagsFromNoteAsync(note: Note): List<Tag> = repository.getTagsFromNote(note.id)


    fun deleteNote (note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun updateNote(
        note: Note,
        oldTags: List<String>,
        newTitle: String,
        newTagsString: String,
        newContent: String
    ) {
        val oldTagsSet = oldTags.toSet()
        val newTagsSet = separateTags(newTagsString).toSet()
        val tagsToDetach = (oldTagsSet subtract newTagsSet).toList()
        val tagsToAttach = (newTagsSet subtract oldTagsSet).toList()
        repository.updateNote(note, newTitle, newContent, tagsToDetach, tagsToAttach)
    }

    private fun separateTags(tagsString: String): List<String> {
        return tagsString
            ?.split("\\s+".toRegex())
            .flatMap {tag -> tag.split("#")}
            .filter { tag -> tag != "" }
    }

}