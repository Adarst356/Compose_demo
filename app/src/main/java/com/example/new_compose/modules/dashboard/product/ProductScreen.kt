// Ported from the "Stockroom" HTML product catalog design to Jetpack Compose
package com.example.new_compose.modules.dashboard.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.new_compose.core.composables.ErrorTextView
import com.example.new_compose.core.composables.Loader
import com.example.new_compose.core.network.UiState
import com.example.new_compose.modules.dashboard.emi.EmiViewModel
import kotlin.math.roundToInt

object StockroomColors {
    val bg = Color(0xFF12151A)
    val surface = Color(0xFF1B2028)
    val line = Color(0xFF333B47)
    val card = Color(0xFFF4F1E9)
    val cardLine = Color(0xFFD9D3C2)
    val ink = Color(0xFF1A1A17)
    val inkSoft = Color(0xFF5B584E)
    val stamp = Color(0xFFC0392B)
    val stampInk = Color(0xFFF4E4E2)
    val gold = Color(0xFFB08245)
    val textHi = Color(0xFFF2F0E9)
    val textMid = Color(0xFF9CA3AF)

    fun categoryColor(category: String): Color = when (category) {
        "beauty" -> Color(0xFFC0567A)
        "fragrances" -> Color(0xFF7F6BC4)
        "furniture" -> Color(0xFFB08245)
        "groceries" -> Color(0xFF5C8C56)
        else -> Color(0xFF888880)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    mainNavController: NavHostController = rememberNavController(),
    viewModel: ProductViewModel = hiltViewModel()
) {

    val productState by viewModel.productState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Products",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = StockroomColors.bg,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        when (val state = productState) {

            UiState.None -> {}

            UiState.Loading -> {
                Loader()
            }

            is UiState.Error -> {
                ErrorTextView(error = state.message)
            }

            is UiState.Success -> {

                val products = state.data.products.orEmpty()

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 168.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding), // Scaffold padding
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(products.filterNotNull()) { product ->

                        ProductCard(
                            thumbnail = product.thumbnail,
                            title = product.title.orEmpty(),
                            price = product.price ?: 0.0,
                            rating = product.rating ?: 0.0,
                            onClick = {

                            }
                        )

                    }
                }
            }
        }
    }
}


@Composable
fun ProductCard(
    thumbnail: String?=null,
    title: String,
    price: Double,
    rating: Double,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column {

            AsyncImage(
                model = thumbnail,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$$price",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = rating.toString(),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}