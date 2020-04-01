package com.example.networknotes.ui.main

import android.content.Context
import com.example.networknotes.NoteContent
import com.example.networknotes.NoteHeader
import com.example.networknotes.db.sql.SqlDB
import com.example.networknotes.db.sql.TableStructure

class MainPresenter(context: Context, private val currentView: MainContract.View): MainContract.Presenter {

    private val model = SqlDB.getInstance(context)

    init {
        currentView.displayAllNotesHeaders(getNotesList())
    }

    fun getNotesList(): List<NoteHeader> {
        val cursor = model.getNotesList()
        with(cursor) {
            val array = mutableListOf<NoteHeader>()
            while (moveToNext()) {
                array.add(
                    NoteHeader(
                        getInt(0),
                        getString(getColumnIndex(TableStructure.COLUMN_NAME_TITLE))
                    )
                )
            }
            return array
        }
    }

    override fun addNote() {
        currentView.displayNoteFragment(null)
    }

    override fun getNoteContent(noteHeader: NoteHeader) {
        val cursor = model.getNotesContent(noteHeader.id)
        with(cursor) {
            if (moveToNext()) {
                currentView.displayNoteFragment(
                    NoteContent (
                        noteHeader.id,
                        getString(getColumnIndex(TableStructure.COLUMN_NAME_TITLE)),
                        getString(getColumnIndex(TableStructure.COLUMN_NAME_CONTENT))
                    )
                )
            } else {
                currentView.displayMsg("Failed getting note content $count")
            }
        }
    }
}
