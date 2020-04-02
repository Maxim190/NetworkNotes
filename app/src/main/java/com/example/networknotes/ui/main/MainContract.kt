package com.example.networknotes.ui.main

import android.database.Cursor
import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader

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
        fun setListener(listener: ModelCallbackContract)
        fun getNotesContent(id: Int)
        fun getNotesList()
        fun addNewNote(title: String, content: String)
        fun editExistingNote(id: Int, title: String, content: String)
        fun deleteNote(id: Int)
    }
}