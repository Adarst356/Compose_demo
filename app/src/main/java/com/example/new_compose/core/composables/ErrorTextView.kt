package com.example.new_compose.core.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorTextView(error: String? = null, modifier: Modifier = Modifier.fillMaxSize(), buttonName:String="Retry", onRetry:(()->Unit)?=null) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieView(Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 16.dp))
        Text(
            error ?: "No Data Found",
            modifier = Modifier,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error
        )
        onRetry?.let {
            OutlineButton(text = buttonName,modifier=Modifier.padding(top = 16.dp)) {
                onRetry()
            }
        }


    }
}