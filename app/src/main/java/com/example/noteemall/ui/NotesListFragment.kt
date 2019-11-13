package com.example.noteemall.ui

import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.noteemall.R
import com.example.noteemall.adapters.NotesAdapter
import com.example.noteemall.adapters.SwipeToDeleteCallback
import com.example.noteemall.data.Note
import com.example.noteemall.viewModels.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class NotesListFragment : Fragment() {

    private lateinit var notesList: RecyclerView
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel
    private lateinit var notes: List<Note>
    private lateinit var newButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_notes_list, container, false)


        viewModel = activity?.run {
            ViewModelProviders.of(this)[NotesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        notesAdapter = NotesAdapter(requireContext(), viewModel) { position ->
            setUpDetails(position)
        }
        notesList = view.findViewById(R.id.notes_list)
        notesList.adapter = notesAdapter

        viewModel.allNotes.observe(viewLifecycleOwner, Observer { notes ->
            // Update the cached copy of the words in the adapter.
            notes?.let {
                notesAdapter.setNotes(it.toMutableList())
                this.notes = it
//                if (it.isEmpty()) {
//                    view.findViewById<TextView>(R.id.empty_list_message).visibility =
//                        VISIBLE
//                }
            }
        })

        setUpRecyclerViewLayout()

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
        val note = notes?.get(position)
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val fragment = NoteFragment.newInstance(note, viewModel)
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
