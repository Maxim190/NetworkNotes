package com.example.networknotes.ui.main

import androidx.fragment.app.DialogFragment
import com.example.networknotes.NoteHeader
import com.example.networknotes.ui.note.NoteFragment

class MainPresenter(private val currentView: MainContract.View): MainContract.Presenter {

    private val model: MainContract.Model = MainModel()

    init {
        currentView.displayAllNotesHeaders(model.getNotesHeaders())
    }

    override fun addNote() {
        //
    }

    override fun getNoteContent(noteHeader: NoteHeader) {
        val item = model.getNoteContent(noteHeader.id)
    }
}
