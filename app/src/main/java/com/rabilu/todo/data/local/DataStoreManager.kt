package com.rabilu.todo.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "data")

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
        val IS_LOGIN = booleanPreferencesKey("IS_LOGIN")
        val USER = stringPreferencesKey("USER_")
        val LANGUAGE = stringPreferencesKey("LANGUAGE")
        val LAST_SYNC = longPreferencesKey("SYNC")
    }

    val getToken = context.dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN] ?: ""
    }

    val getUserInfo = context.dataStore.data.map { preferences ->
        preferences[USER] ?: ""
    }

    val getRefreshToken = context.dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN] ?: ""
    }

    val lastSync = context.dataStore.data.map { preferences ->
        preferences[LAST_SYNC] ?: 0
    }

    val currentAppLanguage = context.dataStore.data.map { preferences ->
        preferences[LANGUAGE] ?: "en"
    }

    val isLogin = context.dataStore.data.map { preferences ->
        preferences[IS_LOGIN] ?: false
    }

    suspend fun saveToken(accessToken: String) {
        Log.d("TAG", "saveToken: i get called")
        context.dataStore.edit { preferences ->
            preferences[IS_LOGIN] = true
            preferences[ACCESS_TOKEN] = accessToken

        }
    }


    suspend fun saveUserInfo(value: String) {
        context.dataStore.edit { preference ->
            preference[USER] = value
        }
    }

    suspend fun saveLastSync(value: Long) {
        context.dataStore.edit { preference ->
            preference[LAST_SYNC] = value
        }
    }


    suspend fun deleteToken() {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGIN] = false
        }
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}