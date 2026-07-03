package com.example.new_compose.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage

/**
 * @Created by akash on 15-04-2025.
 * Know more about author at https://akash.cloudemy.in
 */
@Composable
  fun ImagePreviewDialog(
    model: Any?,
    onDismiss: (() -> Unit)? = null,
    positiveButtonText: String = "Ok",
    onDone: () -> Unit
) {

    Dialog(onDismissRequest = { }) {
        Column(
            Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceDim,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Image Preview", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(model = model, contentDescription = "Image Preview")

            Row(
                Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                onDismiss?.let {
                    OutlineButton(
                        text = "Cancel",
                        modifier = Modifier.weight(1f)
                    ) {
                        onDismiss.invoke()
                    }
                    RoundedButton(text = positiveButtonText, modifier = Modifier.weight(1f)) {
                        onDone.invoke()
                    }
                }
            }
        }
    }
}