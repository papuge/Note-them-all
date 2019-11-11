package com.example.noteemall.ui

import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.*
import androidx.transition.Visibility
import com.example.noteemall.R
import com.example.noteemall.adapters.NotesAdapter
import com.example.noteemall.adapters.SwipeToDeleteCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesListFragment : Fragment() {

    private lateinit var notesList: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private val notes: List<String> = listOf("note1LOOOOOOOONG", "note2", "note3", "note4")
    private lateinit var newButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_notes_list, container, false)
        notesAdapter = NotesAdapter(requireContext(), notes) { position ->
            setUpDetails(position)
        }
        notesList = view.findViewById(R.id.notes_list)
        notesList.adapter = notesAdapter
        if (notesAdapter.itemCount != 0) {
            setUpRecyclerViewLayout()
        } else {
            view.findViewById<TextView>(R.id.empty_list_message)
                .visibility = VISIBLE
        }

        newButton = view.findViewById(R.id.new_note_button)
        newButton.setOnClickListener {
            redirectToNewFragment()
        }

        return view
    }

    private fun setUpRecyclerViewLayout() {
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
    }

    private fun setUpDetails(position: Int) {
        val note = notes[position]
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = NoteFragment.newInstance(note)
        fragmentTransaction
            ?.addToBackStack(null)
            ?.replace(R.id.fragment_container, fragment)
            ?.commit()
    }

    private fun redirectToNewFragment() {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = NoteFormFragment()
        fragmentTransaction
            ?.addToBackStack(null)
            ?.replace(R.id.fragment_container, fragment)
            ?.commit()
    }
}
