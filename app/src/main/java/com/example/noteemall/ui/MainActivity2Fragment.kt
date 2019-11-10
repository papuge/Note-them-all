package com.example.noteemall.ui

import android.app.Activity
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.*
import com.example.noteemall.R
import com.example.noteemall.adapters.NotesAdapter
import com.example.noteemall.adapters.SwipeToDeleteCallback

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivity2Fragment : Fragment() {

    private lateinit var notesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main2, container, false)
        val notes: List<String> = listOf("note1LOOOOOOOOONG", "note2", "note3", "note4")
        val notesAdapter = NotesAdapter(requireContext(), notes)
        notesList = view.findViewById(R.id.notesList)
        notesList.adapter = notesAdapter
        val currentOrientation = activity?.resources?.configuration?.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            notesList.layoutManager = GridLayoutManager(requireContext(), 3)
        } else {
            notesList.layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            notesList.addItemDecoration(decoration)
            val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(notesAdapter))
            itemTouchHelper.attachToRecyclerView(notesList)
        }
        return view
    }
}
