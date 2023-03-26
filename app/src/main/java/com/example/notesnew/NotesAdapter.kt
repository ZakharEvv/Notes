package com.example.notesnew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesnew.Model.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private lateinit var notes : List<Note>
    private lateinit var onNoteClickListener : OnNoteClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)

        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes.get(position)
        holder.tvName.text = note.name
        holder.tvText.text = note.text
        holder.tvNum.text = ""+(position+1)+"/"

        holder.itemLayout.setOnClickListener{
            onNoteClickListener.onNoteClick(note)
        }
    }
    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes : List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNotes(): List<Note> {
        return ArrayList(notes)
    }

    fun setOnNoteClickListener(onNoteClickListener : OnNoteClickListener){
        this.onNoteClickListener = onNoteClickListener
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName : TextView = itemView.findViewById(R.id.tvNameNote)
        var tvText : TextView = itemView.findViewById(R.id.tvTextNote)
        var tvNum : TextView = itemView.findViewById(R.id.tvNum)
        var itemLayout : LinearLayout = itemView.findViewById(R.id.itemLayout)
    }

    interface OnNoteClickListener{
        fun onNoteClick(note : Note)
    }
}