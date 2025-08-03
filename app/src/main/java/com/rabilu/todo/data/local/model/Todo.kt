package com.rabilu.todo.data.local.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("completed")
    val completed: Boolean = false,
    @SerializedName("todo")
    val todo: String? = null,
    @SerializedName("userId")
    val userId: Int? = null
) : Parcelable