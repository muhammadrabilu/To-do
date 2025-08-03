package com.rabilu.todo.data.local.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("username")
    val username: String? = null
)