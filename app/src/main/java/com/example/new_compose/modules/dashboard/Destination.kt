package com.example.new_compose.modules.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector? = null
) {
    data object Login : Destinations("login", "Login")
    data object SignUp : Destinations("signup", "Sign Up")

    data object Dashboard : Destinations("dashboard", "Dashboard")
    data object Home : Destinations("home", "Home", Icons.Outlined.Home)
    data object History : Destinations("history", "History", Icons.Outlined.History)
    data object Profile : Destinations("profile", "Profile", Icons.Outlined.Person)
    data object Emi : Destinations("emi", "Emi", Icons.Outlined.AddCard)
    data object Product : Destinations("product", "Product", Icons.Outlined.ShoppingBag)
}
