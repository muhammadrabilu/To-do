package com.rabilu.todo.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.rabilu.todo.data.local.DataStoreManager
import com.rabilu.todo.data.local.db.TodoDB
import com.rabilu.todo.data.remote.AuthenticationInterceptor
import com.rabilu.todo.data.remote.NetworkConnectionInterceptor
import com.rabilu.todo.data.remote.Services
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): TodoDB =
        Room.databaseBuilder(app, TodoDB::class.java, "todo_db" + app.packageName)
            .fallbackToDestructiveMigration(true)
            .allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideInterceptor(app: Application, dataStoreManager: DataStoreManager): OkHttpClient =
        OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(NetworkConnectionInterceptor(app))
            .addInterceptor(AuthenticationInterceptor(dataStoreManager)).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://dummyjson.com/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideDataStoreManger(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): Services = retrofit.create(Services::class.java)

}