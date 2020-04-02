package com.example.networknotes.db

data class NoteHeader(
    val id: Int,
    val title: String
)

data class NoteContent(
    val id: Int,
    var title: String,
    var content: String
)

data class NoteContentResponse(
    val result: List<NoteContent>
)

data class NoteHeadersResponse(
    val result: List<NoteHeader>
)