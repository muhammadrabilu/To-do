package com.rabilu.todo.ui.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MySpacer(height: Int = 16, width: Int = 16) = Spacer(Modifier
    .height(height.dp)
    .width(width.dp))