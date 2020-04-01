package com.example.networknotes.ui.note

import android.content.Context
import com.example.networknotes.db.sql.SqlDB

class NotePresenter(context: Context, private val currentView: NoteContract.View): NoteContract.Presenter {

    private val model = SqlDB.getInstance(context)

    override fun addNewNote() {
        with(currentView) {
            if (getNoteContent().isBlank() || getNoteTitle().isBlank()) {
                displayMsg("Fill all the gaps")
                return
            }
            val id = model.addNewNote(getNoteTitle(), getNoteContent())
            if (id == -1L) {
                displayMsg("Failed adding new note")
            } else {
                displayMsg("New note was added successfully")
                closeFragment()
            }
        }
    }

    override fun editExistingNote() {
        with(currentView) {
            val rowAffected: Int = model.editExistingNote(getNoteId(), getNoteTitle(), getNoteContent())
            if (rowAffected == 0) {
                displayMsg("Failed editing note")
            } else {
                displayMsg("Note was edited successfully")
                closeFragment()
            }
        }
    }

    override fun deleteNote() {
        with(currentView){
            val rowAffected = model.deleteNote(getNoteId())
            if (rowAffected == 0) {
                displayMsg("Failed deleting note")
            } else {
                displayMsg("Note was deleted successfully")
                closeFragment()
            }
        }
    }
}