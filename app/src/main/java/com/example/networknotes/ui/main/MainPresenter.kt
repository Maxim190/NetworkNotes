package com.example.networknotes.ui.main

import android.content.Context
import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader
import com.example.networknotes.db.sql.SqlDB

class MainPresenter(context: Context, private val currentView: MainContract.View)
        : MainContract.Presenter, ModelCallbackContract {

    private var model: MainContract.Model = SqlDB.getInstance(context)

    init {
        model.setListener(this)
        model.getNotesList()
    }

    override fun addNote() {
        currentView.displayNoteFragment(null)
    }

    override fun getNoteContent(noteHeader: NoteHeader) {
        model.getNotesContent(noteHeader.id)
    }

    override fun modelCallback(notesContent: List<NoteContent>?,
                               notesHeader: List<NoteHeader>?, errorMsg: String?) {
        with(currentView) {
            if (errorMsg != null) {
                displayMsg(errorMsg)
                return
            }
            when {
                notesContent != null -> {
                    displayNoteFragment(notesContent.first())
                }
                notesHeader != null -> {
                    displayAllNotesHeaders(notesHeader)
                }
                else -> {
                    displayMsg("Unknown error occurred")
                }
            }
        }
    }
}
