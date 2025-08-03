package com.rabilu.todo.data.remote

import com.rabilu.todo.data.local.model.Todo
import com.rabilu.todo.data.remote.model.LoginDetails
import com.rabilu.todo.data.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Services {

    @Headers("isAuthorize: false")
    @POST("auth/login")
    suspend fun signIn(@Body loginDetails: LoginDetails): Response<LoginResponse>

    @POST("todos/add")
    suspend fun addTodo(@Body todo: Todo): Response<Todo>
}