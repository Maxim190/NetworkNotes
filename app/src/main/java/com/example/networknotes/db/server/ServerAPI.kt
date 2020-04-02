package com.example.networknotes.db.server

import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader
import retrofit2.Call
import retrofit2.http.*

interface ServerAPI {

    @GET("/api/notes")
    fun getNotesList(): Call<List<NoteHeader>>

    @GET("/api/notes/{id}")
    fun getNoteContent(
        @Path("id") id: Int
    ): Call<List<NoteContent>>

    @POST("/api/notes")
    fun addNewNote(
        @Query("title") title: String,
        @Query("content") content: String
    )

    @PUT("/api/notes/{id}")
    fun editExistingNote(
        @Path("id") id: Int,
        @Query("title") title: String,
        @Query("content") content: String
    )

    @DELETE("/api/notes/{id}")
    fun deleteNote(
        @Path("id") id: Int
    )
}