package com.rabilu.todo.ui.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabilu.todo.data.remote.model.LoginDetails
import com.rabilu.todo.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val isLoging = runBlocking { authRepository.isLogin.first() }

    val loginResponse = authRepository.loginResponse
    val userInfo = authRepository.userInfo

    fun signIn(loginDetails: LoginDetails) {
        viewModelScope.launch {
            authRepository.signIn(loginDetails)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    init {
        viewModelScope.launch {
            authRepository.getUserInfo()
        }
    }

}