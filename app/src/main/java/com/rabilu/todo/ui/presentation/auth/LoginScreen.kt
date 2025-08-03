package com.rabilu.todo.ui.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabilu.todo.data.remote.Resources
import com.rabilu.todo.data.remote.model.LoginDetails
import com.rabilu.todo.ui.presentation.common.CustomTextField
import com.rabilu.todo.ui.presentation.common.MySpacer
import com.rabilu.todo.ui.presentation.common.ProgressDialog
import com.rabilu.todo.ui.presentation.common.TextFieldProperties
import com.rabilu.todo.ui.theme.Purple40
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Composable
@Destination<RootGraph>
fun LoginScreen(navigator: DestinationsNavigator, authViewModel: AuthViewModel = hiltViewModel()) {


    val context = LocalContext.current

    val loginResponse = authViewModel.loginResponse.collectAsState(Resources.Loading()).value

    val email = remember {
        TextFieldProperties(label = "Username", placeHolderText = "Enter your username")
    }
    val password = remember {
        TextFieldProperties(label = "Password", placeHolderText = "Enter your password")
    }
    var showPassword by remember { mutableStateOf(false) }

    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(loginResponse) {
        when (loginResponse) {
            is Resources.Error -> {
                isLoading = false
                Toast.makeText(context, loginResponse.errorMessage, Toast.LENGTH_SHORT).show()
            }

            is Resources.Loading -> {
                isLoading = loginResponse.isloading
            }

            is Resources.Success -> {
                isLoading = false
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)
            }
        }
    }


    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(modifier = Modifier.padding(32.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
                ) {

                    Text(
                        text = "Let’s Sign You In",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 24.sp,

                            )
                    )
                    MySpacer(height = 8)
                    Text(
                        text = "Welcome back!", style = MaterialTheme.typography.titleMedium.copy(
                            color = Color(0xFF525C67)
                        )
                    )
                    MySpacer(height = 32)

                    CustomTextField(properties = email)
                    CustomTextField(
                        properties = password,
                        trailingIcons = {
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    imageVector = if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    MySpacer(height = 32)

                    Button(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth(),
                        enabled = email.getText().isNotBlank() && password.getText().isNotBlank(),
                        onClick = {
                            authViewModel.signIn(
                                LoginDetails(
                                    password = password.getText().trim(),
                                    username = email.getText()
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple40,
                            disabledContainerColor = Purple40.copy(alpha = 0.3F),
                            disabledContentColor = Color.White,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Sign in")
                    }
                }
            }
        }

    }

    ProgressDialog(isLoading)
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(EmptyDestinationsNavigator)
}