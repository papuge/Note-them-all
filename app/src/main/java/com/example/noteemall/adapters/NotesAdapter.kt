package com.example.noteemall.adapters

import android.content.Context
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteemall.R

class NotesAdapter(
    val context: Context,
    private var notes: List<String>,
    private val clickListener: (position: Int) -> Unit
): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = notes?.size ?: 0

    private fun getNote(position: Int) =  notes?.get(position)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount != 0)
            holder.bind(getNote(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.note_row, parent, false),
            clickListener)
    }

    fun deleteItem(position: Int) {
        // TODO(): Not implemented
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

        fun bind(note: String) {
            header.text = note
            content.text = note
        }

    }
}