package com.example.networknotes.ui.main

import android.content.Context
import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader
import com.example.networknotes.db.server.NetworkService
import com.example.networknotes.db.sql.SqlDB

class MainPresenter(context: Context, private val currentView: MainContract.View)
        : MainContract.Presenter, ModelCallbackContract {

    private var model: MainContract.Model = NetworkService.instance//SqlDB.getInstance(context)

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

    override fun <T> modelCallback(data: List<T>, errorMsg: String?) {
        with(currentView) {
            if (errorMsg != null) {
                displayMsg(errorMsg)
                return
            } else if (data.isEmpty()) {
                displayMsg("Failed receiving data")
                return
            }
            when(data.first()) {
                is NoteContent -> {
                    displayNoteFragment(data[0] as NoteContent)
                }
                is NoteHeader -> {
                    displayAllNotesHeaders(data.filterIsInstance<NoteHeader>())
                }
                else -> {
                    displayMsg("Unknown error occurred")
                }
            }
        }
    }
}
