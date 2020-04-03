package com.example.networknotes.db.server

import android.util.Log
import com.example.networknotes.db.ModelCallbackContract
import com.example.networknotes.db.server.mock.MockServer
import com.example.networknotes.ui.main.MainContract
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor(): MainContract.Model{

    private val url = "https://developer.github.com/v3/"
    private var service: ServerAPI? = null
    private var listener: ModelCallbackContract? = null

    companion object {
        val instance = NetworkService()
    }

    init {
        val interceptor = OkHttpClient.Builder()
            .addInterceptor(MockServer())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(interceptor)
            .build()

        service = retrofit.create(ServerAPI::class.java)
    }

    override fun setListener(listener: ModelCallbackContract) {
        this.listener = listener
    }

    override fun getNotesContent(id: Int) {
        service?.getNoteContent(id)?.enqueue(CallbackHandler{
            listener?.modelCallback(data = listOf(it))
        })
    }

    override fun getNotesList() {
        service?.getNotesList()?.enqueue(CallbackHandler{
            listener?.modelCallback(data = it ?: emptyList())
        })
    }

    override fun addNewNote(title: String, content: String) {
        service?.addNewNote(title, content)?.enqueue(CallbackHandler{
            listener?.modelCallback(listOf(it))
        })
    }

    override fun editExistingNote(id: Int, title: String, content: String) {
        service?.editExistingNote(id, title, content)
    }

    override fun deleteNote(id: Int) {
        service?.deleteNote(id)
    }
}
