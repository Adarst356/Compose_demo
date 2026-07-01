package com.example.new_compose.modules.dashboard.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.new_compose.theme.*

@Composable
fun ProfileScreen(mainNavController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(bottom = 120.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ProfileHeader()
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            ProfileStats()
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
            ProfileMenuSection()
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(GoldTop, GoldBottom)
                    )
                )
                .padding(3.dp)
                .clip(CircleShape)
                .background(ObsidianBottom),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                tint = GoldTop,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Fusion Code",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "fusion.code@example.com",
            color = Color.White.copy(alpha = 0.5f),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = GoldTop),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.height(40.dp)
        ) {
            Text("Edit Profile", color = ObsidianBottom, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProfileStats() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(ObsidianTop)
            .background(LuxuryBorderColor)
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem("Savings", "$24.5k")
        VerticalDivider(color = Color.White.copy(alpha = 0.1f), modifier = Modifier.height(40.dp))
        StatItem("Spending", "$12.8k")
        VerticalDivider(color = Color.White.copy(alpha = 0.1f), modifier = Modifier.height(40.dp))
        StatItem("Points", "1,250")
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.5f),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun ProfileMenuSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp))
    {
        ProfileMenuItem(Icons.Outlined.AccountCircle, "Personal Information")
        ProfileMenuItem(Icons.Outlined.Settings, "Settings")
        ProfileMenuItem(Icons.Outlined.Shield, "Security")
        ProfileMenuItem(Icons.Outlined.Notifications, "Notifications")
        ProfileMenuItem(Icons.AutoMirrored.Outlined.HelpOutline, "Help Center")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileMenuItem(icon = Icons.AutoMirrored.Outlined.Logout, label = "Logout", isDestructive = true)
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    label: String,
    isDestructive: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ObsidianTop)
            .background(LuxuryBorderColor)
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isDestructive) Color.Red.copy(alpha = 0.1f)
                    else GoldTop.copy(alpha = 0.1f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDestructive) Color.Red else GoldTop,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = label,
            color = if (isDestructive) Color.Red else Color.White,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        if (!isDestructive) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.3f)
            )
        }
    }
}
