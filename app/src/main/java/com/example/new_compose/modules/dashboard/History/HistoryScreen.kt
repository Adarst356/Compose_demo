package com.example.new_compose.modules.dashboard.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.new_compose.theme.*

@Composable
fun HistoryScreen(mainNavController: NavHostController) {
    var selectedFilter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        
        // Filter Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf("All", "Income", "Expense").forEach { filter ->
                FilterChip(
                    label = filter,
                    isSelected = selectedFilter == filter,
                    onClick = { selectedFilter = filter }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                HistorySectionHeader("Today")
            }
            items(todayHistory) { history ->
                HistoryItem(history)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                HistorySectionHeader("Yesterday")
            }
            items(yesterdayHistory) { history ->
                HistoryItem(history)
            }
        }
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) GoldTop else ObsidianTop,
        border = if (isSelected) null else BoxBorder(LuxuryBorderColor),
        modifier = Modifier.height(40.dp)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                color = if (isSelected) ObsidianBottom else Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
        }
    }
}

@Composable
private fun BoxBorder(color: Color) = androidx.compose.foundation.BorderStroke(1.dp, color)

@Composable
fun HistorySectionHeader(title: String) {
    Text(
        text = title,
        color = Color.White.copy(alpha = 0.5f),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
    )
}

@Composable
fun HistoryItem(history: HistoryActivityData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(ObsidianTop)
            .background(LuxuryBorderColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Icon Box
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = history.icon,
                contentDescription = null,
                tint = GoldTop,
                modifier = Modifier.size(22.dp)
            )
        }

        // Details
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = history.title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = history.time,
                color = Color.White.copy(alpha = 0.4f),
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Amount & Status
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = history.amount,
                color = if (history.isIncome) colorGreen else Color.White,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Completed",
                color = colorGreen.copy(alpha = 0.7f),
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp
            )
        }
    }
}

data class HistoryActivityData(
    val title: String,
    val time: String,
    val amount: String,
    val isIncome: Boolean,
    val icon: ImageVector
)

val todayHistory = listOf(
    HistoryActivityData("Adobe Suite", "10:45 AM", "-$52.99", false, Icons.Outlined.Brush),
    HistoryActivityData("Amazon Deposit", "09:15 AM", "+$1,200.00", true, Icons.Outlined.ShoppingCart),
    HistoryActivityData("Electricity Bill", "08:30 AM", "-$84.50", false, Icons.Outlined.Lightbulb)
)

val yesterdayHistory = listOf(
    HistoryActivityData("Uber Ride", "07:20 PM", "-$15.00", false, Icons.Outlined.DirectionsCar),
    HistoryActivityData("Gym Membership", "04:00 PM", "-$45.00", false, Icons.Outlined.FitnessCenter),
    HistoryActivityData("Paypal Transfer", "01:30 PM", "+$350.00", true, Icons.Outlined.AccountBalance),
    HistoryActivityData("Grocery Store", "11:00 AM", "-$120.40", false, Icons.Outlined.Store)
)
