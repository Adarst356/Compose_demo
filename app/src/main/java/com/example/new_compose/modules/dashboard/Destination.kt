package com.example.new_compose.modules.dashboard

import androidx.annotation.DrawableRes
import com.example.new_compose.R

sealed class Destinations(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int? = null
) {
    data object Login : Destinations("login", "Login")
    data object SignUp : Destinations("signup", "Sign Up")

    data object Dashboard : Destinations("dashboard", "Dashboard")
    data object Home : Destinations("home", "Home", R.drawable.ic_home)
    data object History : Destinations("history", "History", R.drawable.ic_history)
    data object Profile : Destinations("profile", "Profile", R.drawable.ic_profile)
}