package com.example.networknotes.db.server

import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.ui.main.MainContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor(): MainContract.Model{

    private val url = ""
    private var service: ServerAPI? = null
    private var listener: ModelCallbackContract? = null

    companion object {
        val instance = NetworkService()
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ServerAPI::class.java)

    }

    override fun setListener(listener: ModelCallbackContract) {
        this.listener = listener
    }

    override fun getNotesContent(id: Int) {
        service?.getNoteContent(id)?.enqueue(CallbackHandler{
            listener?.modelCallback(notesContent = it)
        })
    }

    override fun getNotesList() {
        service?.getNotesList()?.enqueue(CallbackHandler{
            listener?.modelCallback(notesHeader = it)
        })
    }

    override fun addNewNote(title: String, content: String) {
        service?.addNewNote(title, content)
    }

    override fun editExistingNote(id: Int, title: String, content: String) {
        service?.editExistingNote(id, title, content)
    }

    override fun deleteNote(id: Int) {
        service?.deleteNote(id)
    }
}
