package com.example.new_compose.modules.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.new_compose.modules.auth.login.LoginScreen
import com.example.new_compose.modules.auth.signup.SignUpScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier
) {
    val mainNavController = rememberNavController()

    NavHost(
        navController = mainNavController,
        startDestination = Destinations.Login.route,
        modifier = modifier
    ) {

        composable(Destinations.Login.route) {
            LoginScreen(mainNavController)
        }

        composable(Destinations.SignUp.route) {
            SignUpScreen(mainNavController)
        }

        composable(Destinations.Dashboard.route) {
            DashboardNavGraph(mainNavController)
        }
    }
}