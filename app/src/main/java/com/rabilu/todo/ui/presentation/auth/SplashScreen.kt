package com.rabilu.todo.ui.presentation.auth

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination<RootGraph>(start = true)
fun SplashScreen(navigator: DestinationsNavigator, authViewModel: AuthViewModel = hiltViewModel()) {


    val isLogin = authViewModel.isLoging

    if (isLogin) {
        navigator.popBackStack()
        navigator.navigate(HomeScreenDestination)
    } else {
        navigator.popBackStack()
        navigator.navigate(LoginScreenDestination)
    }

}