package com.rabilu.todo.ui.presentation.todo

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabilu.todo.data.local.model.Todo
import com.rabilu.todo.data.remote.Resources
import com.rabilu.todo.ui.Greeting
import com.rabilu.todo.ui.presentation.common.ProgressDialog
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Composable
@Destination<RootGraph>
fun TodosScreen(
    navigator: DestinationsNavigator,
    userID: Int,
    todo: Todo? = null,
    todoViewModel: TodoViewModel = hiltViewModel()
) {

    var todoText by remember { mutableStateOf(if (todo != null) todo.todo else "") }
    val context = LocalContext.current
    val todoResponse =
        todoViewModel.todoAddResponse.collectAsState(Resources.Loading(isloading = false)).value
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(todoResponse) {
        when (todoResponse) {
            is Resources.Error -> {
                isLoading = false
                Toast.makeText(context, todoResponse.errorMessage, Toast.LENGTH_SHORT).show()
            }

            is Resources.Loading -> {
                isLoading = todoResponse.isloading
            }

            is Resources.Success -> {
                isLoading = false
                navigator.navigateUp()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color.White
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

                IconButton(onClick = {
                    navigator.navigateUp()
                }, modifier = Modifier.align(Alignment.TopStart)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        modifier = Modifier, value = todoText!!, onValueChange = {
                            todoText = it
                        }, placeholder = {
                            Text("Your to-do.......")
                        }, colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences
                        )
                    )

                    Button(
                        onClick = {
                            if (todoText!!.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please type in your to do...",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }
                            if (todo != null)
                                todoViewModel.updateTodo(todo.copy(todo = todoText))
                            else
                                todoViewModel.addTodo(
                                    Todo(
                                        todo = todoText,
                                        userId = userID,
                                        id = 0
                                    )
                                )

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    ) {
                        Text(
                            if (todo != null) "Update" else "Save"
                        )
                    }
                }
            }
        }
    }

    ProgressDialog(isLoading = isLoading)
}

@Preview(showBackground = true)
@Composable
private fun TodoScreeenPreview() {
    TodosScreen(navigator = EmptyDestinationsNavigator, userID = 1)
}