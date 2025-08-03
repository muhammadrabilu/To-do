package com.rabilu.todo.data.repositories

import com.rabilu.todo.data.remote.Resources
import com.google.gson.Gson
import com.rabilu.todo.data.local.DataStoreManager
import com.rabilu.todo.data.local.model.User
import com.rabilu.todo.data.remote.model.LoginDetails
import com.rabilu.todo.data.remote.Services
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import org.json.JSONObject
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager, private val services: Services
) {

    private val _loginResponse: MutableStateFlow<Resources<String>> =
        MutableStateFlow(Resources.Loading())
    val loginResponse: Flow<Resources<String>> = _loginResponse

    val _userInfo = MutableStateFlow(User())
    val userInfo: Flow<User> = _userInfo

    val isLogin = dataStoreManager.isLogin


    suspend fun signIn(loginDetails: LoginDetails) {

        _loginResponse.value = Resources.Loading(isloading = true)

        try {
            val response = services.signIn(loginDetails)

            if (response.isSuccessful) {
                val body = response.body()
                dataStoreManager.saveToken(accessToken = response.body()!!.accessToken!!)
                val userInfo = User(
                    id = body!!.id,
                    firstName = body.firstName,
                    lastName = body.lastName,
                    image = body.image
                )
                dataStoreManager.saveUserInfo(Gson().toJson(userInfo))
                _loginResponse.value = Resources.Success(data = "Login Successful")
            } else {
                val erro = JSONObject(response.errorBody()?.string()!!)
                _loginResponse.value = Resources.Error(errorMessage = erro.getString("message"))
            }

        } catch (e: Exception) {
            if (e.localizedMessage != null) {
                _loginResponse.value =
                    e.localizedMessage?.let { Resources.Error(errorMessage = it) }!!
            } else {
                _loginResponse.value = Resources.Error(errorMessage = "Unknown error.")
            }
        }
    }

    suspend fun getUserInfo() {
        val info = dataStoreManager.getUserInfo.first()
        val user = Gson().fromJson(info, User::class.java)
        if (user != null) _userInfo.value = user
    }


    suspend fun signOut() {
        try {
            dataStoreManager.deleteToken()
            _loginResponse.value = Resources.Loading()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}