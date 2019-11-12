package com.example.noteemall.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.noteemall.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import android.text.method.ScrollingMovementMethod
import com.example.noteemall.data.Note


class NoteFragment : Fragment() {

    private lateinit var headerTextView: TextView
    private lateinit var tagsChipGroup: ChipGroup
    private lateinit var contentTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_note, container, false)
        headerTextView = view.findViewById(R.id.note_header)
        headerTextView.movementMethod = ScrollingMovementMethod()
        tagsChipGroup = view.findViewById(R.id.tags_chip_group)
        contentTextView = view.findViewById(R.id.note_content)
        contentTextView.movementMethod = ScrollingMovementMethod()

        arguments?.getParcelable<Note>(ARG_NOTE).run {
            headerTextView.text = this?.title ?: ""
            contentTextView.text = this?.content ?: ""

            for (i in 1..5) {
                val chip = Chip(requireContext())
                chip.text = this?.title ?: ""
                chip.setChipBackgroundColorResource(R.color.colorCardBackground)
                chip.isClickable = true
                chip.textSize = resources.getDimension(R.dimen.tag_text_size)
                tagsChipGroup.addView(chip)
            }
        }
        return view
    }

    companion object {

        private val ARG_NOTE: String = "ARG_VALS"

        fun newInstance(note: Note?): NoteFragment {
            val fragment = NoteFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_NOTE, note)
            fragment.arguments = bundle
            return fragment
        }
    }
}
