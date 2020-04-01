package com.example.networknotes.ui.main

import android.database.Cursor
import com.example.networknotes.NoteContent
import com.example.networknotes.NoteHeader

interface MainContract {
    interface View {
        fun displayAllNotesHeaders(array: List<NoteHeader>)
        fun displayNoteFragment(note: NoteContent?)
        fun displayMsg(msg: String)
    }
    interface Presenter {
        fun addNote()
        fun getNoteContent(noteHeader: NoteHeader)
    }
    interface Model {
        fun getNotesContent(id: Int): Cursor
        fun getNotesList(): Cursor
        fun addNewNote(title: String, content: String): Long
        fun editExistingNote(id: Int, title: String, content: String): Int
        fun deleteNote(id: Int): Int
    }
}