package com.rabilu.todo.data.remote

sealed class Resources<T>(
    data: T? = null,
) {
    data class Success<T>(val data: T? = null) : Resources<T>(data)
    data class Loading<T>(val isloading: Boolean = false) :
        Resources<T>()

    data class Error<T>(var errorMessage: String) :
        Resources<T>()
}
