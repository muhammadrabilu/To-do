package com.rabilu.todo.data.remote.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String? = null,
    @SerializedName("username")
    val username: String? = null
)