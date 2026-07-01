package com.example.new_compose.core.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * @Created by akash on 12-03-2025.
 * Know more about author at https://akash.cloudemy.in
 */

@Composable
fun RoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    contentColor: Color? = null,
    containerColor: Color? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor ?: MaterialTheme.colorScheme.primary,
            contentColor = contentColor ?: MaterialTheme.colorScheme.onPrimary
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                text = text,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
fun OutlineButton(
    text: String,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    contentColor: Color? = null,
    containerColor: Color? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(
            width = 1.dp,
            color = contentColor ?: MaterialTheme.colorScheme.primary
        ),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor ?: MaterialTheme.colorScheme.surface,
            contentColor = contentColor ?: MaterialTheme.colorScheme.primary
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                color =  contentColor ?: MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                text = text
            )
        }
    }
}

@Composable
fun OutlineIconButton(
    text: String,
    icon:ImageVector,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    contentColor: Color? = null,

    containerColor: Color? = null, enabled: Boolean = true, onClick: () -> Unit = {}
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(
            width = 1.dp,
            color = contentColor ?: MaterialTheme.colorScheme.primary
        ),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor ?: MaterialTheme.colorScheme.surface,
            contentColor = contentColor ?: MaterialTheme.colorScheme.primary
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = contentColor ?: MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = "Icon", Modifier.padding(end = 8.dp))
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = text
                )

            }
        }
    }
}