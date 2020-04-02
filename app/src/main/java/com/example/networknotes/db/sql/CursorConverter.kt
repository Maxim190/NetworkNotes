package com.example.networknotes.db.sql

import android.database.Cursor
import android.provider.BaseColumns
import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader


object CursorConverter{
    fun convertToNoteContent(cursor: Cursor): List<NoteContent> {
        with(cursor) {
            val array = ArrayList<NoteContent>()
            while (moveToNext()) {
                array.add(
                    NoteContent(
                        getInt(0),
                        getString(getColumnIndex(TableStructure.COLUMN_NAME_TITLE)),
                        getString(getColumnIndex(TableStructure.COLUMN_NAME_CONTENT))
                    )
                )
            }
            return array
        }
    }

    fun convertToNoteHeader(cursor: Cursor): List<NoteHeader> {
        with(cursor) {
            val array = ArrayList<NoteHeader>()
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
}
