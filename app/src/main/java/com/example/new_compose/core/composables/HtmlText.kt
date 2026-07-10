package com.example.new_compose.core.composables

import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView

/**
 * @Created by akash on 07-07-2025.
 * Know more about author at https://akash.cloudemy.in
 */


@Composable
fun HtmlText(html: String, color: Color, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
                setTextColor(color.toArgb())
                isSingleLine = true
                ellipsize = TextUtils.TruncateAt.MARQUEE
                marqueeRepeatLimit = -1 // infinite
                isSelected = true       // required for marquee
                isFocusable = true
                isFocusableInTouchMode = true
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setHorizontallyScrolling(true)
            }
        },
        modifier = modifier
    )
}