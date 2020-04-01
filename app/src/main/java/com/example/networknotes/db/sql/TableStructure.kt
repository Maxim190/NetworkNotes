package com.example.networknotes.db.sql

import android.provider.BaseColumns


object TableStructure: BaseColumns {
    const val TABLE_NAME = "notes"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
}

