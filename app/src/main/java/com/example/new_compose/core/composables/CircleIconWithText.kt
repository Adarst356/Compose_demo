package com.example.new_compose.core.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Created by akash on 12-03-2025.
 * Know more about author at https://akash.cloudemy.in
 */

@Composable
fun CircleIconWithText(
    @DrawableRes iconResId: Int,
    text: String,
    modifier: Modifier = Modifier,
    iconPadding: Int = 8,
    color: Color? = null,
    iconColor: Color? = MaterialTheme.colorScheme.onPrimaryContainer,
    textColor: Color? = null,
    fontSize: Int? = null,
    iconSize: Int = 48,
    textPadding: Int = 4,
    fontWeight:FontWeight?=null
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        CircleIcon(
            iconResId,
            Modifier.size(iconSize.dp),
            iconColor = iconColor,
            iconPadding = iconPadding,
            color = color
        )
        Text(
            text = text,
            color = textColor ?: MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            lineHeight = ((fontSize ?: 12) + 6).sp,
            fontSize = (fontSize ?: 12).sp,
            modifier = Modifier.padding(top = textPadding.dp),
            fontWeight = fontWeight
        )
    }
}

@Composable
fun CircleIcon(
    @DrawableRes iconResId: Int,
    modifier: Modifier = Modifier,
    color: Color? = null,
    iconColor: Color? = MaterialTheme.colorScheme.onPrimaryContainer,
    iconPadding: Int = 8
) {
    Image(
        painter = painterResource(iconResId),
        contentDescription = "Icons",
        colorFilter = iconColor?.let { ColorFilter.tint(color = it) },
        modifier = modifier
            .background(
                color ?: MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            )
            .padding(iconPadding.dp)
    )
}