package com.example.networknotes.db.server

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallbackHandler<T>(private val handler: (List<T>?)-> Unit): Callback<List<T>> {
    override fun onFailure(call: Call<List<T>>, t: Throwable) {
        handler.invoke(null)
    }

    override fun onResponse(call: Call<List<T>>, response: Response<List<T>>) {
        handler.invoke(response.body())
    }
}