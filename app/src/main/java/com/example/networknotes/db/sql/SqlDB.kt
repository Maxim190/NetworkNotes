package com.example.networknotes.db.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.ui.main.MainContract


class SqlDB private constructor(val context: Context):
    SQLiteOpenHelper(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ), MainContract.Model {

    private var listener: ModelCallbackContract? = null

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ${TableStructure.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableStructure.COLUMN_NAME_TITLE} TEXT," +
                "${TableStructure.COLUMN_NAME_CONTENT} TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${TableStructure.TABLE_NAME}")
        onCreate(db)
    }

    override fun setListener(listener: ModelCallbackContract) {
        this.listener = listener
    }

    override fun getNotesContent(id: Int) {
        val projection = arrayOf(
            BaseColumns._ID,
            TableStructure.COLUMN_NAME_TITLE,
            TableStructure.COLUMN_NAME_CONTENT
        )
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$id")
        val result = CursorConverter.convertToNoteContent(
            readableDatabase.query(
                TableStructure.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
        )
        listener?.modelCallback(notesContent = result)
    }

    override fun getNotesList(){
        val projection = arrayOf(
            BaseColumns._ID,
            TableStructure.COLUMN_NAME_TITLE
        )
        val result= CursorConverter.convertToNoteHeader(
            readableDatabase.query(
                TableStructure.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )
        )
        listener?.modelCallback(notesHeader = result)
    }

    override fun addNewNote(title: String, content: String) {
        val values = ContentValues().apply {
            put(TableStructure.COLUMN_NAME_TITLE, title)
            put(TableStructure.COLUMN_NAME_CONTENT, content)
        }
        writableDatabase.insert(
            TableStructure.TABLE_NAME,
            null,
            values
        )
    }

    override fun editExistingNote(id: Int, title: String, content: String){
        val values = ContentValues().apply {
            put(TableStructure.COLUMN_NAME_TITLE, title)
            put(TableStructure.COLUMN_NAME_CONTENT, content)
        }
        val whereClause = "${BaseColumns._ID} = ?"
        val whereArgs = arrayOf("$id")
        writableDatabase.update(
            TableStructure.TABLE_NAME,
            values,
            whereClause,
            whereArgs
        )
    }

    override fun deleteNote(id: Int) {
        val whereClause = "${BaseColumns._ID} = ?"
        val whereArgs = arrayOf("$id")
        writableDatabase.delete(
            TableStructure.TABLE_NAME,
            whereClause,
            whereArgs
        )
    }

//    private fun selectAll() {
//        val projection = arrayOf(
//            BaseColumns._ID,
//            TableStructure.COLUMN_NAME_TITLE,
//            TableStructure.COLUMN_NAME_CONTENT
//        )
//        val cursor =
//            readableDatabase.query(
//                TableStructure.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//            )
//        val result = ArrayList<NoteContent>()
//        cursor.moveToFirst()
//        if(cursor.count == 0) {return}
//        do {
//            result.add(
//                NoteContent(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2)
//                )
//            )
//        } while (cursor.moveToNext())
//
//        for(i in result) {
//            Log.e("Notes", "id:${i.id} title:${i.title} content:${i.content}")
//        }
//    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "notes_storage.db"

        private var instance: SqlDB? = null

        fun getInstance(context: Context): SqlDB {
            if (instance == null) {
                instance =
                    SqlDB(context)
            }
            return instance as SqlDB
        }
    }
}