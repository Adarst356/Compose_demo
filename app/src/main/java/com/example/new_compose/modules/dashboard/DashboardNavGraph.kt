package com.example.new_compose.modules.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.new_compose.core.composables.LuxuryBottomNav
import com.example.new_compose.modules.auth.login.LoginScreen
import com.example.new_compose.modules.auth.signup.SignUpScreen
import com.example.new_compose.modules.dashboard.emi.EmiScreen
import com.example.new_compose.modules.dashboard.history.HistoryScreen
import com.example.new_compose.modules.dashboard.home.HomeScreen
import com.example.new_compose.modules.dashboard.product.ProductScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DashboardNavGraph(mainNavController: NavHostController) {

    val bottomNavController = rememberNavController()
    val selectedRoute =
        bottomNavController.currentBackStackEntryAsState().value?.destination?.route

    val bottomItems = listOf(
        Destinations.Home,
        Destinations.History,
        Destinations.Profile,
        Destinations.Product,
        Destinations.Emi
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF050507))
    ) {

        NavHost(
            navController = bottomNavController,
            startDestination = Destinations.Home.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(Destinations.Login.route) { LoginScreen(mainNavController) }
            composable(Destinations.SignUp.route) { SignUpScreen(mainNavController) }
            composable(Destinations.Home.route) { HomeScreen(mainNavController) }
            composable(Destinations.History.route) { HistoryScreen(mainNavController) }
            composable(Destinations.Product.route) { ProductScreen(mainNavController) }
            composable(Destinations.Emi.route) { EmiScreen(mainNavController) }
        }

        LuxuryBottomNav(
            items = bottomItems,
            selectedRoute = selectedRoute,
            onItemClick = { item ->
                bottomNavController.navigate(item.route) {
                    bottomNavController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    3
                    restoreState = true
                }
            }
        )
    }
}