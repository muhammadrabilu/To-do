package com.rabilu.todo.ui.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rabilu.todo.ui.theme.Purple40

@Composable
fun ProgressDialog(isLoading: Boolean, modifier: Modifier = Modifier) {

    if (isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = modifier) {
            Dialog(
                onDismissRequest = { }, properties = DialogProperties(dismissOnBackPress = false)
            ) {
                CircularProgressIndicator(color = Purple40)
            }
        }
    }
}