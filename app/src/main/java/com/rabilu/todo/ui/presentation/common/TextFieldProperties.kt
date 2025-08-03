package com.rabilu.todo.ui.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization

data class TextFieldProperties(
    private val text: MutableState<String> = mutableStateOf(""),
    val label: String,
    val placeHolderText: String,
    var isError: MutableState<Boolean> = mutableStateOf(false),
    var isEnable: Boolean = true,
    private var selected: Boolean = false,
    var errorMessage: MutableState<String> = mutableStateOf(""),
    val keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        capitalization = KeyboardCapitalization.Words,
    )
) {
    fun isEmpty(): Boolean {
        if (text.value.isEmpty()) {
            setError("$label is empty")
        }
        return text.value.isEmpty()
    }

    fun setError(errorMessage: String) {
        isError.value = true
        this.errorMessage = mutableStateOf(errorMessage)
    }

    fun getSelected() = selected
    fun setSelected(value: Boolean) {
        selected = value
    }

    fun getText() = text.value
    fun getTextOrNull(): String? {
        return text.value.ifEmpty {
            return null
        }
    }

    fun getError() = errorMessage.value


    fun setText(text: String) {
        this.text.value = text
    }

}