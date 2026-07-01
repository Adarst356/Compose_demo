package com.example.new_compose.modules.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.new_compose.modules.dashboard.home.HomeScreen
import com.example.new_compose.modules.dashboard.history.HistoryScreen
import com.example.new_compose.modules.dashboard.profile.ProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardNavGraph(mainNavController: NavHostController) {

    val bottomNavController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawer(
        drawerState = drawerState,
        onRouteSelected = { item ->
            scope.launch { drawerState.close() }
            when (item) {
                Destinations.Home, Destinations.History, Destinations.Profile -> {
                    bottomNavController.navigate(item.route) {
                        bottomNavController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                else -> {
                    mainNavController.navigate(item.route)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Dashboard") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(bottomNavController)
            }
        ) { innerPadding ->
            NavHost(
                navController = bottomNavController,
                startDestination = Destinations.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Destinations.Home.route) { HomeScreen(mainNavController) }
                composable(Destinations.History.route) { HistoryScreen(mainNavController) }
                composable(Destinations.Profile.route) { ProfileScreen(mainNavController) }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(bottomNavController: NavHostController) {
    val destinations = listOf(
        Destinations.Home,
        Destinations.History,
        Destinations.Profile,
    )

    val selectedItem =
        bottomNavController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        destinations.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                selected = selectedItem == item.route,
                icon = {
                    item.icon?.let {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = item.label,
                        )
                    }
                },
                label = { Text(item.label, maxLines = 1) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
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
    }
}