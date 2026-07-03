package com.example.new_compose.modules.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.new_compose.theme.*

@Composable
fun HomeScreen(
    mainNavController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(bottom = 100.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            WelcomeHeader()
        }

        item {
            BalanceCard()
        }

        item {
            QuickActions()
        }

        item {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        items(recentActivities) { activity ->
            ActivityItem(activity)
        }
    }
}

@Composable
fun WelcomeHeader() {
    Column {
        Text(
            text = "Welcome back,",
            color = Color.White.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Fusion Code",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BalanceCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(24.dp),
                spotColor = GoldBottom.copy(alpha = 0.5f)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(GoldTop, GoldBottom)
                )
            )
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Balance",
                    color = ObsidianBottom.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    imageVector = Icons.Outlined.Visibility,
                    contentDescription = null,
                    tint = ObsidianBottom,
                    modifier = Modifier.size(20.dp)
                )
            }

            Text(
                text = "$ 124,500.00",
                color = ObsidianBottom,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "**** 4290",
                    color = ObsidianBottom.copy(alpha = 0.6f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "VISA",
                    color = ObsidianBottom,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun QuickActions() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionItem(Icons.AutoMirrored.Outlined.Send, "Send")
        ActionItem(Icons.Outlined.AccountBalanceWallet, "Wallet")
        ActionItem(Icons.Outlined.BarChart, "Stats")
        ActionItem(Icons.Outlined.MoreHoriz, "More")
    }
}

@Composable
fun ActionItem(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(ObsidianTop)
                .background(LuxuryBorderColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = GoldTop,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun ActivityItem(activity: ActivityData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ObsidianTop)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White.copy(alpha = 0.05f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = activity.icon,
                contentDescription = null,
                tint = GoldTop
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = activity.title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = activity.date,
                color = Color.White.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = activity.amount,
            color = if (activity.isIncome) colorGreen else Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

data class ActivityData(
    val title: String,
    val date: String,
    val amount: String,
    val isIncome: Boolean,
    val icon: ImageVector
)

val recentActivities = listOf(
    ActivityData("Apple Music", "24 Oct 2023", "-$12.99", false, Icons.Outlined.MusicNote),
    ActivityData("Freelance Payment", "23 Oct 2023", "+$2,500.00", true, Icons.Outlined.Work),
    ActivityData("Starbucks Cafe", "22 Oct 2023", "-$6.50", false, Icons.Outlined.Coffee),
    ActivityData("Netflix Subscription", "20 Oct 2023", "-$18.99", false, Icons.Outlined.Tv)
)
