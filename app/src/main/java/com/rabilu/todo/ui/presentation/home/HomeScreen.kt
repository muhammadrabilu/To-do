package com.rabilu.todo.ui.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rabilu.todo.R
import com.rabilu.todo.data.local.model.Todo
import com.rabilu.todo.data.local.model.User
import com.rabilu.todo.ui.presentation.auth.AuthViewModel
import com.rabilu.todo.ui.presentation.common.MySpacer
import com.rabilu.todo.ui.presentation.common.TodoItem
import com.rabilu.todo.ui.presentation.todo.TodoViewModel
import com.rabilu.todo.ui.theme.Purple40
import com.rabilu.todo.ui.theme.PurpleGrey80
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.generated.destinations.TodosScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination<RootGraph>
fun HomeScreen(
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = hiltViewModel(),
    todoViewModel: TodoViewModel = hiltViewModel()
) {

    val userInfo = authViewModel.userInfo.collectAsState(User()).value
    val todos = todoViewModel.allTodos.collectAsState(listOf()).value

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navigator.navigate(TodosScreenDestination(userID = userInfo.id!!))
        }, containerColor = Purple40, contentColor = Color.White) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

    }) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1F),

                    ) {

                    Surface(color = PurpleGrey80, shape = RoundedCornerShape(100)) {
                        AsyncImage(
                            model = userInfo.image,
                            placeholder = painterResource(R.drawable.baseline_person_24),
                            error = painterResource(R.drawable.baseline_person_24),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(RoundedCornerShape(100))
                                .size(35.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Hi, ${userInfo.lastName}", style = TextStyle(
                                fontSize = 18.sp,
                            )
                        )
                        Text(
                            "Good day", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color(0xFF78828A),
                            )
                        )
                    }

                }

                TextButton(
                    onClick = {
                        authViewModel.signOut()
                        navigator.popBackStack()
                        navigator.navigate(LoginScreenDestination)
                    }, shape = RoundedCornerShape(8), contentPadding = PaddingValues(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Log out ", color = Color.Red)
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = null
                        )
                    }
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (todos.isEmpty())
                    Column(
                        Modifier
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Note,
                            contentDescription = null,
                            Modifier
                                .size(100.dp),
                            tint = Color(0xFF78828A)
                        )
                        MySpacer(height = 8)
                        Text(
                            "Your To-do is empty", style = TextStyle(
                                color = Color(0xFF78828A),
                            )
                        )
                    }
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(todos) {
                        TodoItem(
                            todo = it,
                            onDelete = {
                                todoViewModel.deleteTodo(it)
                            },
                            onComplete = { todoViewModel.completeTodo(it.copy(completed = !it.completed)) },
                            onClick = {
                                Log.d("TAG", "HomeScreen: $it ")
                                navigator.navigate(
                                    TodosScreenDestination(
                                        todo = it, userID = userInfo.id!!
                                    )
                                )
                            })
                        MySpacer(height = 16)
                    }
                }
            }

        }
    }
}