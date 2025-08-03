package com.rabilu.todo.data.remote

import com.google.gson.annotations.SerializedName

data class RequestResponse<T>(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: T? = null,
)