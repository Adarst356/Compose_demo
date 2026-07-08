package com.example.new_compose.modules.dashboard

import androidx.compose.foundation.background
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
import com.example.new_compose.modules.dashboard.history.HistoryScreen
import com.example.new_compose.modules.dashboard.home.HomeScreen
import com.example.new_compose.modules.dashboard.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardNavGraph(mainNavController: NavHostController) {

    val bottomNavController = rememberNavController()
    val selectedRoute = bottomNavController.currentBackStackEntryAsState().value?.destination?.route

    val bottomItems = listOf(
        Destinations.Home,
        Destinations.History,
        Destinations.Profile
    )

    val currentTitle = when (selectedRoute) {
        Destinations.Home.route -> "Home"
        Destinations.History.route -> "History"
        Destinations.Profile.route -> "Profile"
        else -> "Dashboard"
    }

    Scaffold(
        topBar = {
            com.example.new_compose.core.composables.LuxuryTopBar(
                title = currentTitle
            )
        },
        bottomBar = {
            LuxuryBottomNav(
                items = bottomItems,
                selectedRoute = selectedRoute,
                onItemClick = {
                    item ->
                    bottomNavController.navigate(item.route) {
                        bottomNavController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Destinations.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF050507))
                .then(Modifier.padding(innerPadding))
        ) {
            composable(Destinations.Home.route) { HomeScreen(mainNavController) }
            composable(Destinations.History.route) { HistoryScreen(mainNavController) }
            composable(Destinations.Profile.route) { ProfileScreen(mainNavController) }
        }
    }
}