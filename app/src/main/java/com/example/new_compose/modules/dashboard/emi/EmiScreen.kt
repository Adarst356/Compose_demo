package com.example.new_compose.modules.dashboard.emi

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun EmiScreen(
    mainNavController: NavHostController = rememberNavController(),
    viewModel: EmiViewModel = hiltViewModel()
) {

}