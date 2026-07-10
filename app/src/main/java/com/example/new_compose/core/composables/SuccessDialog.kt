package com.example.new_compose.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.new_compose.theme.colorGreen


/**
 * @Created by akash on 19-03-2025.
 * Know more about author at https://akash.cloudemy.in
 */
@Preview(showBackground = true)
@Composable
fun SuccessDialog(
    title: String? = null,
    message: String? = null,
    isVisible: Boolean = true,
    dismissOnTouchOutside: Boolean = true,
    dismissOnButtonClick: Boolean = true,
    action: String? = null,
    onDismiss: () -> Unit = {},
    onAction: (() -> Unit)? = null
) {
    var showDialog by remember(isVisible) { mutableStateOf(isVisible) }
    if (showDialog) Dialog(onDismissRequest = {
        if (dismissOnTouchOutside) {
            showDialog = false
            onDismiss.invoke()
        }
    }) {
        SuccessView(
            title = title,
            message = message,
            action = action,
            onDismiss = {
                if (dismissOnButtonClick || dismissOnTouchOutside) showDialog = false
                onDismiss.invoke()
            },
            onAction = {
                if (dismissOnButtonClick || dismissOnTouchOutside) showDialog = false
                onAction?.invoke()
            }
        )
    }
}

@Composable
fun SuccessView(
    title: String?=null,
    message: String?=null,
    action: String? = null,
    onDismiss: () -> Unit,
    onAction: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
            .wrapContentHeight()
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            colorGreen, Color(0xFF81C784)
                        )
                    )
                )
        ) {
            Text(
                text = title?:"Message",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 28.sp, fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            IconButton(
                onClick = onDismiss, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }
        }

        // Circle Icon
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Success",
            tint = colorGreen,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(64.dp)
                .offset(y = (-32).dp) // Adjust position
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
        )


        Text(text = message?:"Operation successful",
            color = colorGreen,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp.takeIf { action != null } ?: 48.dp))
        action?.let {
            OutlineButton(it,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onAction?.invoke() })
        }
        // Bottom Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF81C784), colorGreen
                        )
                    )
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDialog(
    title: String = "Error",
    message: String? = "Something went wrong. Please try again",
    isVisible: Boolean = true,
    dismissOnTouchOutside: Boolean = true,
    dismissOnButtonClick: Boolean = true,
    onDismiss: () -> Unit = {}
) {
    var showDialog by remember(isVisible) { mutableStateOf(isVisible) }
    if (showDialog) Dialog(onDismissRequest = {
        if (dismissOnTouchOutside) {
            showDialog = false
            onDismiss.invoke()
        }
    }) {
        ErrorView(
            title = title, modifier = Modifier.padding(16.dp), onCloseClicked = {
                if (dismissOnButtonClick||dismissOnTouchOutside) {
                    showDialog = false
                }
                onDismiss.invoke()
            },
            message = message ?: "Something went wrong. Please try again"
        )
    }
}

@Composable
fun ErrorView(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    action:String?=null,
    onAction: (() -> Unit)?=null,
    onCloseClicked: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp)
            )
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.error,
                            MaterialTheme.colorScheme.error.copy(red = .9f)
                        )
                    )
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp, fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onError,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            onCloseClicked?.let {
                IconButton(
                    onClick = {
                        it.invoke()
                    }, modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White
                    )
                }
            }

        }

        // Circle Icon
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(64.dp)
                .offset(y = (-32).dp) // Adjust position
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
        )


        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 48.dp)
        )
        action?.let {
            OutlineButton(it,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onAction?.invoke() })
        }
        // Bottom Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.error.copy(red = .9f),
                            MaterialTheme.colorScheme.error
                        )
                    )
                )
        )
    }
}

