package com.example.networknotes.ui.main

import com.example.networknotes.NoteHeader

class MainModel: MainContract.Model {
    override fun getNoteContent(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getNotesHeaders(): List<NoteHeader> {
        return listOf(
            NoteHeader(1, "hi"),
            NoteHeader(2, "my"),
            NoteHeader(3, "dear"),
            NoteHeader(4, "owl")
        )
    }
}