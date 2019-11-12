package com.example.noteemall.adapters

import android.content.Context
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteemall.R
import com.example.noteemall.data.Note
import com.example.noteemall.viewModels.NotesViewModel

class NotesAdapter(
    val context: Context,
    private val notesViewModel: NotesViewModel,
    private val clickListener: (position: Int) -> Unit
): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var notes: MutableList<Note> = mutableListOf()

    override fun getItemCount(): Int = notes.size

    fun getNote(position: Int) =  notes?.get(position)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount != 0)
            holder.bind(getNote(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(inflater.inflate(R.layout.note_row, parent, false),
            clickListener) { position ->
            deleteItem(position)
        }
        return viewHolder
    }

    fun deleteItem(position: Int) {
        notesViewModel.deleteNote(notes[position])
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setNotes(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, listener: (position: Int) -> Unit,
                     delete_callback: (position: Int) -> Unit):
        RecyclerView.ViewHolder(itemView) {
        private val header: TextView = itemView.findViewById(R.id.note_header_preview)
        private val content: TextView = itemView.findViewById(R.id.note_сontent_preview)

        init {
            val position = adapterPosition
            itemView.setOnClickListener {
                val position = adapterPosition
                listener(position)
            }
            val deleteButton: ImageButton? = itemView.findViewById(R.id.delete_landscape)
                deleteButton?.setOnClickListener {
                    val position = adapterPosition
                    delete_callback(position)
                }
        }

        fun bind(note: Note) {
            header.text = note.title
            content.text = note.content
        }

    }
}