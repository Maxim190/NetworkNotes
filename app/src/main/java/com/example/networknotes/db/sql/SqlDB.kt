package com.example.networknotes.db.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.networknotes.ui.main.MainContract


class SqlDB private constructor(val context: Context):
    SQLiteOpenHelper(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ), MainContract.Model {

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

    override fun getNotesContent(id: Int): Cursor {
        val projection = arrayOf(
            TableStructure.COLUMN_NAME_TITLE,
            TableStructure.COLUMN_NAME_CONTENT
        )
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$id")
        return readableDatabase.query(
            TableStructure.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
    }

    override fun getNotesList(): Cursor {
        val projection = arrayOf(
            BaseColumns._ID,
            TableStructure.COLUMN_NAME_TITLE
        )
        return readableDatabase.query(
            TableStructure.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
    }

    override fun addNewNote(title: String, content: String): Long {
        val values = ContentValues().apply {
            put(TableStructure.COLUMN_NAME_TITLE, title)
            put(TableStructure.COLUMN_NAME_CONTENT, content)
        }
        return writableDatabase.insert(
            TableStructure.TABLE_NAME,
            null,
            values
        )
    }

    override fun editExistingNote(id: Int, title: String, content: String): Int {
        val values = ContentValues().apply {
            put(TableStructure.COLUMN_NAME_TITLE, title)
            put(TableStructure.COLUMN_NAME_CONTENT, content)
        }
        val whereClause = "${BaseColumns._ID} = ?"
        val whereArgs = arrayOf("$id")
        return writableDatabase.update(
            TableStructure.TABLE_NAME,
            values,
            whereClause,
            whereArgs
        )
    }

    override fun deleteNote(id: Int): Int {
        val whereClause = "${BaseColumns._ID} = ?"
        val whereArgs = arrayOf("$id")
        return writableDatabase.delete(
            TableStructure.TABLE_NAME,
            whereClause,
            whereArgs
        )
    }

    fun selectAll(): Cursor {
        val projection = arrayOf(
            BaseColumns._ID,
            TableStructure.COLUMN_NAME_TITLE,
            TableStructure.COLUMN_NAME_CONTENT
        )
        return readableDatabase.query(
            TableStructure.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
    }

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