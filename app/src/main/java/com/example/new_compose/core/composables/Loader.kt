package com.example.new_compose.core.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoaderDialog() {
    Dialog(onDismissRequest = {}) {
        DotAnimation(numberOfDots = 4, dotColor = MaterialTheme.colorScheme.secondaryContainer, dotSize = 15.dp)
    }
}@Composable
fun Loader(modifier: Modifier = Modifier) {
    DotAnimation(modifier=modifier,numberOfDots = 4, dotColor = MaterialTheme.colorScheme.secondaryContainer, dotSize = 15.dp)

}