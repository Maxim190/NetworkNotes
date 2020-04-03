package com.example.networknotes.db.server.mock

import android.util.Log
import com.example.networknotes.db.server.ServerAPI
import okhttp3.*


class MockServer: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val callMethod: String? =
            chain.call()
                .request()
                .tag(retrofit2.Invocation::class.java)
                ?.method()
                ?.name

        var responseMsg: String = ""
        when (callMethod) {
            ServerAPI::getNotesList.name -> {
                responseMsg = RESPONSE_NOTES_LIST
            }
            ServerAPI::getNoteContent.name -> {
                responseMsg = RESPONSE_GET_NOTE_CONTENT
            }
        }

        return Response.Builder()
            .code(200)
            .message(responseMsg)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(ResponseBody.create(MediaType.parse("application/json"), responseMsg))
            .addHeader("content-type", "application/json")
            .build()
    }

}