package com.example.noteemall.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteemall.data.Note
import com.example.noteemall.data.NotesDatabase
import com.example.noteemall.data.NotesRepository
import kotlinx.coroutines.launch

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

    fun insertNote (note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote (note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

}