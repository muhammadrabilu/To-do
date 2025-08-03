package com.rabilu.todo.ui.presentation.common

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyTextfieldColor() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color(0xFFE3E7EC),
    focusedBorderColor = Color(0xFFE3E7EC),
    unfocusedPlaceholderColor = Color(0xFF9CA4AB),
    unfocusedContainerColor = Color(0xFFFEFEFE),
    focusedContainerColor = Color(0xFFFEFEFE)
)