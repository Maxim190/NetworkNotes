package com.example.networknotes.ui.main.RcyclerView

import com.example.networknotes.db.NoteHeader

interface Callback {
    fun onItemClicked(item: NoteHeader)
}