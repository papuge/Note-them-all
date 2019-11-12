package com.example.noteemall.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders

import com.example.noteemall.R
import com.example.noteemall.data.Note
import com.example.noteemall.viewModels.NotesViewModel
import java.time.LocalDate

class NoteFormFragment : Fragment() {

    private lateinit var titleEditText: EditText
    private lateinit var tagsEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var doneButton: Button
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_note_form, container, false)

        titleEditText = view.findViewById(R.id.form_title_edit_text)
        tagsEditText = view.findViewById(R.id.form_tags_edit_text)
        contentEditText = view.findViewById(R.id.form_content_edit_text)
        doneButton = view.findViewById(R.id.form_done_button)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[NotesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        doneButton.setOnClickListener {
            var title: String
            var tagsString: String?
            var content: String?
            if (TextUtils.isEmpty(titleEditText.text.toString())) {
                title = LocalDate.now().toString()
            } else {
                title = titleEditText.text.toString()
            }
            tagsString = tagsEditText.text.toString()
            content = contentEditText.text.toString()
            val note = Note(title, content)
            viewModel.insertNote(note)
            activity?.supportFragmentManager?.popBackStack()
        }
        return view
    }

    private fun processTags() {
        // TODO(): split string to tags
    }
}
