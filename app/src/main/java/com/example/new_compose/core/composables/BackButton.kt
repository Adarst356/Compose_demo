package com.example.new_compose.core.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

/**
 * @Created by akash on 20-03-2025.
 * Know more about author at https://akash.cloudemy.in
 */
@Composable
fun BackButton(mainNavController: NavHostController?=null,
               color:Color?=null,
               modifier: Modifier=Modifier,
               onBackPressed:(()->Unit)?=null) {
    IconButton(modifier=modifier,onClick = {
        onBackPressed?.invoke()
        mainNavController?.popBackStack()
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "Back button",
            tint = color?: LocalContentColor.current
        )
    }

}

