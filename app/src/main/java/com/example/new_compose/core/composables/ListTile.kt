package com.example.new_compose.core.composables



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @Created by akash on 20-03-2025.
 * Know more about author at https://akash.cloudemy.in
 */
@Composable
fun ListTile(
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    verticalAlignment: Alignment.Vertical=Alignment.Top,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp),
        verticalAlignment = verticalAlignment
    ) {
        // Leading icon or image
        leading?.let {
            leading()
            Spacer(modifier = Modifier.width(12.dp))
        }

        // Title and subtitle column
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            title()
            subtitle?.let {
                Spacer(modifier = Modifier.height(4.dp))
                it()
            }
        }

        // Trailing widget (optional)
        trailing?.let {
            Spacer(modifier = Modifier.width(12.dp))
            trailing()
        }
    }
}
