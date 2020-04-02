package com.example.networknotes.ui.note

import android.content.Context
import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader
import com.example.networknotes.db.sql.SqlDB

class NotePresenter(context: Context, private val currentView: NoteContract.View)
        : NoteContract.Presenter{

    private val model = SqlDB.getInstance(context)

    override fun addNewNote() {
        with(currentView) {
            if (getNoteContent().isBlank() || getNoteTitle().isBlank()) {
                displayMsg("Fill all the gaps")
                return
            }
            model.addNewNote(getNoteTitle(), getNoteContent())
            closeFragment()
        }
    }

    override fun editExistingNote() {
        with(currentView) {
            model.editExistingNote(getNoteId(), getNoteTitle(), getNoteContent())
            closeFragment()
        }
    }

    override fun deleteNote() {
        with(currentView) {
            model.deleteNote(getNoteId())
            closeFragment()
        }
    }
}