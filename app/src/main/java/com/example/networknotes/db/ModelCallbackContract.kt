package com.example.networknotes.db

interface ModelCallbackContract {
    fun modelCallback(
        notesContent: List<NoteContent>? = null,
        notesHeader: List<NoteHeader>? = null,
        errorMsg: String? = null
    )
}