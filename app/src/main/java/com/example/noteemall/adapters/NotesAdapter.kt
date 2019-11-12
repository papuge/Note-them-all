package com.example.noteemall.adapters

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteemall.R
import com.example.noteemall.data.Note

class NotesAdapter(
    val context: Context,
    private val clickListener: (position: Int) -> Unit
): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>()

    override fun getItemCount(): Int = notes.size

    private fun getNote(position: Int) =  notes?.get(position)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount != 0)
            holder.bind(getNote(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.note_row, parent, false),
            clickListener)
    }

    internal fun deleteItem(position: Int) {
        // TODO(): Not implemented
    }

    internal fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, listener: (position: Int) -> Unit):
        RecyclerView.ViewHolder(itemView) {
        private val header: TextView = itemView.findViewById(R.id.note_header_preview)
        private val content: TextView = itemView.findViewById(R.id.note_сontent_preview)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener(position)
            }
        }

        fun bind(note: Note) {
            header.text = note.title
            content.text = note.content
        }

    }
}