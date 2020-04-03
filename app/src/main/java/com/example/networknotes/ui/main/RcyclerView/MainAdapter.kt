package com.example.networknotes.ui.main.RcyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networknotes.db.NoteHeader
import com.example.networknotes.R

class MainAdapter(val items: List<NoteHeader>, val callback: Callback): RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycle_view_item, parent, false)
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.textView)

        fun bind(noteHeader: NoteHeader) {
            title.text = noteHeader.title
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(noteHeader)
            }
        }
    }
}