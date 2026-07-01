package com.example.new_compose.core.composables


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.new_compose.modules.dashboard.Destinations
import com.example.new_compose.theme.*

@Composable
fun LuxuryBottomNav(
    items: List<Destinations>,
    selectedRoute: String?,
    onItemClick: (Destinations) -> Unit,
    modifier: Modifier = Modifier
) {
    val navHeight = 76.dp
    val itemBoxSize = 56.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                ambientColor = ObsidianBottom,
                spotColor = ObsidianBottom
            )
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(ObsidianTop, ObsidianBottom)
                )
            )
            .background(LuxuryBorderColor)
            .navigationBarsPadding()
            .height(navHeight)
    ) {
        val itemCount = items.size
        if (itemCount > 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    val isActive = item.route == selectedRoute

                    // Pill Pop Animation
                    val pillScale by animateFloatAsState(
                        targetValue = if (isActive) 1f else 0.7f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),
                        label = "pillScale"
                    )
                    val pillAlpha by animateFloatAsState(
                        targetValue = if (isActive) 1f else 0f,
                        animationSpec = tween(300),
                        label = "pillAlpha"
                    )

                    // Icon Animation States
                    val iconScale by animateFloatAsState(
                        targetValue = if (isActive) 1.1f else 1.0f,
                        animationSpec = tween(400),
                        label = "iconScale"
                    )
                    val iconRotation by animateFloatAsState(
                        targetValue = if (isActive) -5f else 0f,
                        animationSpec = tween(400),
                        label = "iconRotation"
                    )
                    val iconColor by animateColorAsState(
                        targetValue = if (isActive) LuxuryActiveIcon else LuxuryInactiveIcon,
                        animationSpec = tween(400),
                        label = "iconColor"
                    )

                    Box(
                        modifier = Modifier
                            .size(itemBoxSize)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { onItemClick(item) },
                        contentAlignment = Alignment.Center
                    ) {
                        // The Pop Gold Pill (Behind the icon)
                        Box(
                            modifier = Modifier
                                .scale(pillScale)
                                .size(itemBoxSize)
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(20.dp),
                                    spotColor = GoldBottom.copy(alpha = 0.35f * pillAlpha)
                                )
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(GoldTop, GoldBottom),
                                        start = Offset(0f, 0f),
                                        end = Offset(100f, 100f)
                                    ),
                                    alpha = pillAlpha
                                )
                        )

                        item.icon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = item.label,
                                tint = iconColor,
                                modifier = Modifier
                                    .size(24.dp)
                                    .scale(iconScale)
                                    .rotate(iconRotation)
                            )
                        }
                    }
                }
            }
        }
    }
}
