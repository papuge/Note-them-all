package com.example.noteemall.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.noteemall.data.Note

class NotesAdapter(
    context: Context,
    private var notes: MutableLiveData<List<Note>>
): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = notes.value?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}