package com.example.networknotes.ui.note

interface NoteContract {
    interface View {
        fun getNoteTitle(): String
        fun getNoteContent(): String
        fun getNoteId(): Int
        fun displayMsg(msg: String)
        fun closeFragment()
    }
    interface Presenter {
        fun addNewNote()
        fun editExistingNote()
        fun deleteNote()
    }
}