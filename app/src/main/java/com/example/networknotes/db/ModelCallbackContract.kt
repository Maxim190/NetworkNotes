package com.example.networknotes.db

interface ModelCallbackContract {
    fun <T> modelCallback(
        data: List<T>,
        errorMsg: String? = null
    )
}