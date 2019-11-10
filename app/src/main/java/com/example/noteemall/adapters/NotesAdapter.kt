package com.example.noteemall.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.noteemall.R
import com.example.noteemall.data.Note

class NotesAdapter(
    val context: Context,
    private var notes: List<String>
): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int = notes?.size ?: 0

    private fun getNote(position: Int) =  notes?.get(position)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemCount != 0)
            holder.bind(getNote(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.note_row, parent, false))
    }

    fun deleteItem(position: Int) {
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val header: TextView = itemView.findViewById(R.id.noteHeaderPreview)
        private val content: TextView = itemView.findViewById(R.id.noteContentPreview)

        fun bind(note: String) {
            header.text = note
            content.text = note
        }

    }
}