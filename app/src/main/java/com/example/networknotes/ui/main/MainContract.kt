package com.example.networknotes.ui.main

import com.example.networknotes.NoteHeader

interface MainContract {
    interface View {
        fun displayAllNotesHeaders(array: List<NoteHeader>)
    }
    interface Presenter {
        fun addNote()
        fun getNoteContent(noteHeader: NoteHeader)
    }
    interface Model {
        fun getNoteContent(id: Int)
        fun getNotesHeaders(): List<NoteHeader>
    }
}