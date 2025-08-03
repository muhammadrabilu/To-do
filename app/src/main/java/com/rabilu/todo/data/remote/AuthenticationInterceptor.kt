package com.rabilu.todo.data.remote

import android.util.Log
import com.rabilu.todo.data.local.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(private val dataStoreManager: DataStoreManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original: Request = chain.request()

        var loginToken: String? = runBlocking {
            dataStoreManager.getToken.first()
        }


        if (original.headers["isAuthorize"] == "false") {
            loginToken = null
        }

        return chain.proceed(
            chain.request().newBuilder()
                .also {
                    if (loginToken != null) {
                        Log.d("TAG", "intercept: token null $loginToken")
                        it.addHeader(
                            "Authorization",
                            "Bearer $loginToken"
                        )
                    }
                }
                .build()
        )
    }


}